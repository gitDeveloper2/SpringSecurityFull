package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
	@Autowired
	private UsersRepo usersRepo;

	public void create(Users user) {
		usersRepo.save(user);
	}
	public Users read(Long id) {
		return usersRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
	}
	public List<Users> readAll() {
		return usersRepo.findAll();
	}
	public void Update(Users user) {
		Users userToUpdate=usersRepo.findById(user.getId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
		if(userToUpdate!=null) {
			
			userToUpdate.setName(user.getName());
			String roles=user.getRoles();
			roles=roles.substring(1, roles.length()-1);
			System.out.println(roles);
			userToUpdate.setRoles(roles);
			usersRepo.save(userToUpdate);
		}
			
	}
	public void delete(Long id) {
		usersRepo.deleteById(id);
	}
}
