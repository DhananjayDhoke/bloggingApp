package com.bloggingapp.demo.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","posts"})
public class User {
    
//	public User(Integer id) {
//		super();
//		this.id = id;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "user_name",nullable = false,length = 100)
	@NotEmpty
	@Size(min = 3,max=15,message = "username must be minimum of 3 char")
	private String name;
	
	@NotEmpty
	@Email(message="Email should be valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max=10, message = "password must be within 3 to 10 character")
	private String password;
	
	@NotEmpty
	private String about;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
}
