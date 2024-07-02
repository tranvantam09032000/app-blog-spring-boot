package com.springboot.appspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.appspringboot.abstracts.AuditableAbstract;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited(targetAuditMode = NOT_AUDITED)
@Table(name = "like_of_post")
public class LikeOfPost extends AuditableAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonIgnore
    Post post;
    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonIgnore
    Author author;
}