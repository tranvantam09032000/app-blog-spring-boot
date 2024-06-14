package com.springboot.appspringboot.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonManagedReference
    Author author;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    Category category;
    String title;
    String subTitle;
    String content;
    String thumbnail;
    Date createdAt;
    Date updatedAt;
    Boolean published;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    @JsonManagedReference
    Set<Tag> tags;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Comment> comments;
}