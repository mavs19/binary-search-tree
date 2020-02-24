/* David Perry
 * 30010019
 * 17 February 2020
 * Balanced Binary Search Tree test class
 */
package portfolioq2;

import static junit.framework.TestCase.assertEquals;

// Test class
public class BinarySearchTreeTest {

    // A Binary Search Tree is created, some objects are created for testing purposes
    BinarySearchTree<Parts> parts = new BinarySearchTree<>();
    Parts p1 = new Parts("Piston");
    Parts p2 = new Parts("Brake Pad");
    Parts p3 = new Parts("Gasket");
    Parts p4 = new Parts("Washer");

    // Testing the add method, using the length method to ensure three items added to the tree
    @org.junit.Test
    public void testAdd() {

        parts.add(p1.getName());
        parts.add(p2.getName());
        parts.add(p3.getName());
        assertEquals(3, parts.length());
    }

    // Testing the delete method, using the length method to ensure 1 item remains on the tree
    @org.junit.Test
    public void testDelete() {
        parts.add(p1.getName());
        parts.add(p2.getName());
        parts.delete(p1.getName());
        assertEquals(1, parts.length());
    }

    // Testing the Find max method, adding some parts to the list, comparing find max with assert equals by hard coding the max value
    @org.junit.Test
    public void testFindMax() {
        parts.add(p1.getName());
        parts.add(p2.getName());
        parts.add(p3.getName());
        parts.add(p4.getName());
        assertEquals("Washer", parts.findMax());
    }

    // Testing the Find min method, adding some parts to the list, comparing find max with assert equals by hard coding the min value
    @org.junit.Test
    public void testFindMin() {
        parts.add(p1.getName());
        parts.add(p2.getName());
        parts.add(p3.getName());
        parts.add(p4.getName());
        assertEquals("Brake Pad", parts.findMin());
    }

    // Testing the height method, with 2 items added the height should equal 2. Using assert equals to run the test
    @org.junit.Test
    public void testHeight() {
        parts.add(p1.getName());
        parts.add(p2.getName());
        assertEquals(2, parts.height());
    }

}
