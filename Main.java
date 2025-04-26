import model.Follow;
import model.Post;
import model.User;
import service.FollowService;
import service.PostService;
import service.UserService;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

public static void main(String[] args) {
        UUID userId = null;
        UUID[] postIds = new UUID[1000];
        int postIndex = 0;
        UserService userService = new UserService(20);
        PostService postService = new PostService(20);
        FollowService followService = new FollowService(20);

        int stepCode = 20;
        while (stepCode != 0){
            printMenu();
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1 ->{
                    User user = new User();
                    System.out.print("Your first name?: ");
                    user.setFirstName(scannerStr.nextLine());
                    System.out.print("Your last name?: ");
                    user.setLastName(scannerStr.nextLine());
                    System.out.print("Your age?: ");
                    user.setAge(scannerInt.nextInt());
                    System.out.print("Create password: ");
                    user.setPassword(scannerStr.nextLine());
                    System.out.print("Create your nick name: ");
                    user.setNickName(scannerStr.nextLine());
                    String str = userService.add(user);
                    userId = user.getId();
                    System.out.println(str);
                }
                case 2 ->{
                    System.out.print("Enter your nick name: ");
                    String nick = scannerStr.nextLine();
                    System.out.print("Enter your Password: ");
                    String password = scannerStr.nextLine();
                    User currentUser = userService.login(nick, password);
                    if(currentUser != null ){
                        int stepCode2 = 20;
                        while(stepCode2 != 0){
                            printUserMenu();
                            stepCode2 = scannerInt.nextInt();
                            switch (stepCode2){
                                case 1 ->{
                                    int stepCode3 = 20;
                                    while (stepCode3 != 0) {
                                        printPostMenu();
                                        stepCode3 = scannerInt.nextInt();
                                        switch (stepCode3){
                                            case 1 ->{
                                                Post post = new Post();
                                                System.out.print("Video? Photo?: ");
                                                post.setUrl(scannerStr.nextLine());
                                                System.out.print("Whats a " + post.getUrl()+"?: ");
                                                post.setTitle(scannerStr.nextLine());
                                                post.setUserId(userId);
                                                String res = postService.add(post);
                                                System.out.println(res);
                                            }
                                            case 2 ->{
                                                Object[] post = postService.list();
                                                int index = 1;
                                                for(Object object: post){
                                                    if(object != null){
                                                        Post posts = (Post) object;
                                                        System.out.print(index++ + ")");
                                                        System.out.println(posts);
                                                        postIds[postIndex++] = posts.getId();
                                                    }
                                                }

                                            }
                                            case 3 ->{
                                                Post postUpdate = new Post();
                                                Object[] post = postService.list();
                                                int index = 1;
                                                for(Object object: post){
                                                    if(object != null){
                                                        Post posts = (Post) object;
                                                        System.out.print(index++ + " >> ");
                                                        System.out.println(posts);
                                                    }
                                                }
                                                System.out.print("Whats post your want to Update? Select and Enter to number: ");
                                                int n = scannerInt.nextInt();

                                                System.out.print("Video? Photo?: ");
                                                postUpdate.setUrl(scannerStr.nextLine());
                                                System.out.print("Whats a " + postUpdate.getUrl()+"?: ");
                                                postUpdate.setTitle(scannerStr.nextLine());
                                                postUpdate.setUserId(userId);

                                                Object object = (Object) postUpdate;

                                                String str = postService.upDate(postIds[n-1],object);
                                                System.out.println(str);
                                            }
                                            case 4 ->{
                                                Object[] post = postService.list();
                                                int index = 1;
                                                for(Object object: post){
                                                    if(object != null){
                                                        Post posts = (Post) object;
                                                        System.out.print(index++ + " >> ");
                                                        System.out.println(posts);
                                                    }
                                                }
                                                System.out.print("Whats post your want to delete? Select and Enter to number: ");
                                                int n = scannerInt.nextInt();
                                                postService.delete(postIds[n-1]);
                                                System.out.println("Your post Deleted. Check list.");
                                            }
                                            case 0 ->{
                                                stepCode3 = 0;
                                            }
                                        }
                                    }
                                }
                                case 2 ->{
                                    System.out.print("Enter to user nickname: ");
                                    String searchingNickName = scannerStr.nextLine();
                                    Object[] saarchingUsers = userService.searchUser(searchingNickName);
                                    int count = 0;
                                    for(Object o: saarchingUsers){
                                        if(o != null){
                                            User user = (User) o;
                                            System.out.print(count++ + ")");
                                            System.out.println(user);
                                        }
                                    }
                                }
                                case 3 ->{
                                    System.out.print("Enter to Username: ");
                                    String username = scannerStr.nextLine();
                                    User user = userService.searchUserByUsername(username);
                                    if (user == null) {
                                        System.out.println("user not found");
                                    }else {
                                        String result = followService.add(new Follow(currentUser.getId(), user.getId()));
                                        System.out.println(result);
                                    }
                                }
                                case 4 ->{
                                    Follow[] followings = followService.listSubscribedFollowers(currentUser.getId());
                                    for(Follow follow: followings){
                                        User user = (User) userService.getById(follow.getToUserId());
                                        System.out.println(user);
                                    }
                                }
                                case 5 -> {
                                    Follow[] followings = followService.listAcceptedFollowers(currentUser.getId());
                                    for (Follow follow : followings) {
                                        User user = (User) userService.getById(follow.getFromUserId());
                                        System.out.println(user);
                                    }
                                }
                            }

                        }
                    }else{
                        System.out.println("Invalid nick name or password!!!");
                    }

                }
                case 0 ->{
                    stepCode = 0;
                }
            }
        }


    }

    public static void printMenu() {
        System.out.println("*  *  *  *  *  *  *  *  *");
        System.out.print("""
                *      1. Register      *
                *      2. Login         *
                *      0. Exit          *
                """);
        System.out.println("*  *  *  *  *  *  *  *  *");
        System.out.print("   Enter to: ");
    }

    public static void printUserMenu() {
        System.out.println(" Welcome to instagram mate !!!");
        System.out.println("*  *  *  *  *  *  *  *  *  *");
        System.out.print("""
                *    1. Post               *
                *    2. Search Users       *
                *    3. Subscribe          *
                *    4. List my followings *
                *    5. List my followers  *
                *    0. Exit               *
                """);
        System.out.println("*  *  *  *  *  *  *  *  *  *");
        System.out.print("   Enter to: ");
    }

    public static void printPostMenu() {
        System.out.println("*  *  *  *  *  *  *  *  *");
        System.out.print("""
                *    1. Create post     *
                *   2. List my posts    *
                *  3.  Update my post   *
                *    4. Delete post     *
                *      0. Return        *
                """);
        System.out.println("*  *  *  *  *  *  *  *  *");
        System.out.print("   Enter to: ");
    }
}