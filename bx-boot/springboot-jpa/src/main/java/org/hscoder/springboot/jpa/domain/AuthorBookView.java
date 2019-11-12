package org.hscoder.springboot.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 视图
 * <pre>
 *
 * CREATE OR REPLACE VIEW public.v_author_book AS
 *  SELECT b.id, b.title, a.name as author
 *    FROM author a, book b
 *   WHERE a.id = b.author_id;
 * </pre>
 */
@Entity
@Table(name = "v_author_book")
public class AuthorBookView {

    @Id
    private Long id;
    private String title;

    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_hometown")
    private String authorHometown;

    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorHometown() {
        return authorHometown;
    }

    public void setAuthorHometown(String authorHometown) {
        this.authorHometown = authorHometown;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
