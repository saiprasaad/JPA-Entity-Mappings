package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Gender;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class JpaMappingsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingsApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("Saiprasaad");
		user.setEmail("saiprasaad1999@gmail.com");
		
		UserProfile userProfile = new UserProfile();
		userProfile.setAddress("2951 S King Drive");
		userProfile.setBirthday(LocalDate.of(1999, 11, 18));
		userProfile.setGender(Gender.MALE);
		userProfile.setPhoneNumber("7738005463");
		
		user.setUserProfile(userProfile);
		userProfile.setUser(user);
		
		userRepository.save(user);
		
		Post post = new Post("Graduated", "At Illinois Tech");
		Comment comment1 = new Comment("Good Job!");
		Comment comment2 = new Comment("Congratulations!");
		Comment comment3 = new Comment("Great Work !");
		
		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);
		
		postRepository.save(post);
		
		Movie movie1 = new Movie("Inception", "Inception is a science fiction thriller that explores the concept of shared dreaming.");
		Movie movie2 = new Movie("The Shawshank Redemption", "The Shawshank Redemption tells the story of Andy Dufresne, a banker wrongly imprisoned for the murder of his wife and her lover.");
		Movie movie3 = new Movie("Parasite", "Parasite  is a South Korean dark comedy thriller that explores class disparity and social inequality.");
		
		Tag tag1 = new Tag("Comedy");
		Tag tag2 = new Tag("Thriller");
		Tag tag3 = new Tag("Mystery");
		
		movie1.getTags().add(tag1);		
		movie1.getTags().add(tag2);
		
		movie2.getTags().add(tag2);		
		movie2.getTags().add(tag3);
		
		movie3.getTags().add(tag1);		
		movie3.getTags().add(tag3);
		
		tag1.getMovies().add(movie1);
		tag1.getMovies().add(movie3);
		
		tag2.getMovies().add(movie1);
		tag2.getMovies().add(movie2);
		
		tag3.getMovies().add(movie2);
		tag3.getMovies().add(movie3);
		
		movieRepository.save(movie1);
		movieRepository.save(movie2);
		movieRepository.save(movie3);
	}
	
	
	
}
