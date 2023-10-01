package com.dkhang.contact_manager_project.services;

import com.dkhang.contact_manager_project.repositories.GroupRepository;
import com.dkhang.contact_manager_project.repositories.MemberRepository;
import com.dkhang.contact_manager_project.repositories.UserRepository;

public class ApplicationService {
	
	private final GroupRepository groupRepository = new GroupRepository();
	private final UserRepository userRepository = new UserRepository();
	private final MemberRepository memberRepository = new MemberRepository();
}
