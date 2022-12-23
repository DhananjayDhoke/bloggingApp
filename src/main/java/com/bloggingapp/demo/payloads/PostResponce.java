package com.bloggingapp.demo.payloads;

import java.util.List;

import com.bloggingapp.demo.entites.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostResponce {
    
	private List<Post> content;
	private Integer pageNumber;
	private Integer pageSize;
	private long totalElements;
	private Integer totalPages;
	private boolean lastPage;
}
