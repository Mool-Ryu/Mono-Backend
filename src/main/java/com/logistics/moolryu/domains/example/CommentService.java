package com.logistics.moolryu.domains.example;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	@Transactional
	public CommentCreateResponseDto createComment(Long feedId, CommentCreateRequestDto requestDto) {
		Comment comment = Comment.builder()
			.content(requestDto.getContent())
			.build();
		commentRepository.save(comment);

		return CommentCreateResponseDto.builder()
			.commentId(comment.getId())
			.content(comment.getContent())
			.build();
	}

}