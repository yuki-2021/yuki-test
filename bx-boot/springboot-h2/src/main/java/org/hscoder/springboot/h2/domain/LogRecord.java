package org.hscoder.springboot.h2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="log_record")
    public class LogRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String level;

    private String message;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
