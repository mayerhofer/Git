/**
 * 
 */
package com.infoGlobo.joinha.tests;

import com.infoGlobo.joinha.models.Like;
import com.infoGlobo.joinha.services.IRepository;
import com.infoGlobo.joinha.services.LikeManager;
import com.infoGlobo.joinha.services.LikeRepository;

import junit.framework.TestCase;

import org.junit.Assert;

/**
 * @author Ricardo
 *
 */
public class LikeManagerTest extends TestCase {

	private static IRepository<Like> repository;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		repository = new LikeRepository();
		repository.removeAll();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetLikes_1()
	{
		String expected = "[{\"postId\":1,\"likes\":2,\"dislikes\":3},{\"postId\":2,\"likes\":3,\"dislikes\":1},{\"postId\":3,\"likes\":2,\"dislikes\":1}]";
		Like l1 = new Like(1, 2, 3);
		Like l2 = new Like(2, 3, 1);
		Like l3 = new Like(3, 2, 1);
		
		repository.add(l1);
		repository.add(l2);
		repository.add(l3);
		
		LikeManager manager = new LikeManager(repository);
		String actual = manager.getLikes();
		
		Assert.assertArrayEquals("Text result should be same.", expected.toCharArray(), actual.toCharArray());
	}
	
	public void testGetLikes_2()
	{
		String expected = "[{\"postId\":1,\"likes\":4,\"dislikes\":6},{\"postId\":2,\"likes\":70,\"dislikes\":101}]";
		Like l1 = new Like(1, 4, 6);
		Like l2 = new Like(2, 70, 101);
		
		repository.add(l1);
		repository.add(l2);
		
		LikeManager manager = new LikeManager(repository);
		String actual = manager.getLikes();
		
		Assert.assertArrayEquals("Text result should be same.", expected.toCharArray(), actual.toCharArray());
	}
	
	public void testGetLikes_3()
	{
		String expected = "[]";
		LikeManager manager = new LikeManager(repository);
		String actual = manager.getLikes();
		
		Assert.assertArrayEquals("Text result should be same.", expected.toCharArray(), actual.toCharArray());
	}
	
	public void testIncLikes_like()
	{
		Like l1 = new Like(5, 10, 3);
		Like l2 = new Like(2, 3, 1);
		Like l3 = new Like(3, 2, 1);
		
		repository.add(l1);
		repository.add(l2);
		repository.add(l3);
		
		LikeManager manager = new LikeManager(repository);
		manager.incLike("10");
		
		Like found = repository.GetById(5);
		Assert.assertNotNull(found);
		Assert.assertEquals(11, found.getLikes());
		
		Like found2 = repository.GetById(10);
		Assert.assertNull(found2);
		
		Like found3 = repository.GetById(2);
		Assert.assertNotNull(found3);
		Assert.assertEquals(3, found3.getLikes());
	}
	
	public void testIncLikes_dislike()
	{
		Like l1 = new Like(5, 10, 3);
		Like l2 = new Like(2, 3, 1);
		Like l3 = new Like(3, 2, 1);
		
		repository.add(l1);
		repository.add(l2);
		repository.add(l3);
		
		LikeManager manager = new LikeManager(repository);
		manager.incLike("7");
		
		Like found = repository.GetById(3);
		Assert.assertNotNull(found);
		Assert.assertEquals(2, found.getDislikes());
		
		Like found2 = repository.GetById(7);
		Assert.assertNull(found2);
		
		Like found3 = repository.GetById(5);
		Assert.assertNotNull(found3);
		Assert.assertEquals(3, found3.getDislikes());
	}
}
