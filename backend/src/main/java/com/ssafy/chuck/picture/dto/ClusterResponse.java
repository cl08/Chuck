package com.ssafy.chuck.picture.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClusterResponse {
	private String rep_image;
	private List<String> path_list;
}
