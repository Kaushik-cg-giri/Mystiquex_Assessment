package com.movieflix.dto;

import java.util.List;

public record MoviePageResponse ( List<MovieDTO> movieDtos,
								 Integer pageNumber,
								 Integer pageSize,
								 long totalElemnts,
								 Integer totalPage,
								 boolean isLast) {}
