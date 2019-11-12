package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.domain.Book;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom {

    private final EntityManager em;

    @Autowired
    public BookRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(Book.class);
    }

    @Override
    public PageResult<Book> search(String type, String title, boolean hasFav, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<Book> root = cq.from(Book.class);

        List<Predicate> conds = new ArrayList<>();

        //按类型检索
        if (!StringUtils.isEmpty(type)) {
            conds.add(cb.equal(root.get("type").as(String.class
            ), type));
        }

        //标题模糊搜索
        if (!StringUtils.isEmpty(title)) {
            conds.add(cb.like(root.get("title").as(String.class
            ), "%" + title + "%"));
        }

        //必须被收藏过
        if (hasFav) {
            conds.add(cb.gt(root.get("favCount").as(Integer.class
            ), 0));
        }

        //count 数量
        cq.select(cb.count(root)).where(conds.toArray(new Predicate[0]));
        Long count = (Long) em.createQuery(cq).getSingleResult();

        if (count <= 0) {
            return PageResult.empty();
        }

        //list 列表
        cq.select(root).where(conds.toArray(new Predicate[0]));

        //获取排序
        List<Order> orders = toOrders(pageable, cb, root);

        if (!CollectionUtils.isEmpty(orders)) {
            cq.orderBy(orders);
        }


        TypedQuery<Book> typedQuery = em.createQuery(cq);

        //设置分页
        typedQuery.setFirstResult(pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Book> list = typedQuery.getResultList();

        return PageResult.of(count, list);

    }

    private List<Order> toOrders(Pageable pageable, CriteriaBuilder cb, Root<?> root) {

        List<Order> orders = new ArrayList<>();
        if (pageable.getSort() != null) {
            for (Sort.Order o : pageable.getSort()) {
                if (o.isAscending()) {
                    orders.add(cb.asc(root.get(o.getProperty())));
                } else {
                    orders.add(cb.desc(root.get(o.getProperty())));
                }
            }
        }

        return orders;
    }


    public List<Tuple> groupCount(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<Book> root = cq.from(Book.class);

        Path<String> typePath = root.get("type");

        //查询type/count(*)/sum(favCount)
        cq.select(cb.tuple(typePath,cb.count(root).alias("count"), cb.sum(root.get("favCount"))));
        //按type分组
        cq.groupBy(typePath);
        //按数量排序
        cq.orderBy(cb.desc(cb.literal("count")));

        //查询出元祖
        TypedQuery<Tuple> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }
}
