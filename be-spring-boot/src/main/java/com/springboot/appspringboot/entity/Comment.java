package com.springboot.appspringboot.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.appspringboot.abstracts.AuditableAbstract;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited(targetAuditMode = NOT_AUDITED)
@Table(name = "comment")
public class Comment extends AuditableAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String fullName;
    String content;
    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonIgnore
    Post post;
}
