package com.dkhang.contact_manager_project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String password;
	private String username;
	private boolean isUsed;
}
