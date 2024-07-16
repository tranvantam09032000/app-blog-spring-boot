package com.springboot.appspringboot.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.appspringboot.abstracts.AuditableAbstract;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "author")
@Builder
public class Author extends AuditableAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String email;
    String password;
    String firstName;
    String lastName;
    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    Set<Post> posts;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    Set<LikeOfPost> likeOfPosts;
}
