package com.csc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Iterator;

public class SortedArrayListTest_backup {

    private static String inputStr;

    private static InputStream is = System.in;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(is));

    private static SortedArrayList<Client> clients = new SortedArrayList<>();
    private static SortedArrayList<Activity> activities = new SortedArrayList<>();

    private static String INPUT_FILE_PATH = "src/main/java/com/csc/activityList.txt";

    private static String OUTPUT_FILE_PATH = "src/main/java/com/csc/result.txt";

    public static void main(String[] args) throws IOException {
        init();
        listMenu();

        while (true) {
            if ("e".equals(inputStr)) {
                listActivity();
            } else if ("c".equals(inputStr)) {
                listClient();
            } else if ("b".equals(inputStr)) {
                buyTicket();
            } else if ("r".equals(inputStr)) {
                cancelTicket();
            } else if ("f".equals(inputStr)) {
                System.exit(0);
            } else {
                System.out.println("please input a validate character!");
                inputStr = br.readLine();
            }
        }
    }

    private static void listMenu() throws IOException {
        System.out.println("MENU");
        System.out.println("f - to finish running the program.");
        System.out.println("e - to display on the screen the information about all the events.");
        System.out.println("c - to display on the screen the information about all the clients.");
        System.out.println("b - to update the stored data when tickets are bought by one of the registered clients.");
        System.out.println("r - to update the stored data when a registered client cancels/returns tickets.");
        System.out.println("------------------------------------------");
        System.out.println("type a letter and press Enter");
        inputStr = br.readLine();
    }

    private static void init() throws IOException {
        readActivity();
    }

    private static void readActivity() throws IOException {
        FileReader fr = new FileReader(INPUT_FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String line;
        int counter = 0;

        int clientNum = 0;
        int activityNum = 0;

        boolean isReadClientBlock = false;
        while ((line = br.readLine()) != null) {
            if (!isReadClientBlock) {
                if (counter == 0) {
                    activityNum = Integer.valueOf(line);
                    counter++;
                    continue;
                }

                if ("".equals(line)) {
                    clientNum = Integer.valueOf(br.readLine());
                    counter++;
                    isReadClientBlock = true;
                    continue;
                }

                if (1 == counter % 2) {
                    Activity activity = new Activity();
                    activity.setActivityName(line);
                    line = br.readLine();
                    activity.setRemainNum(Integer.valueOf(line));
                    counter += 2;
                    activities.add(activity);
                }
            } else {
                Client client = new Client();
                client.setFirstName(line.split(" ")[0]);
                client.setLastName(line.split(" ")[1]);
                clients.add(client);
                counter++;
            }
        }

        br.close();
        fr.close();
    }

    private static void listActivity() throws IOException {
        for (Activity a : activities) {
            System.out.println(a);
        }
        inputStr = br.readLine();
    }

    private static void listClient() throws IOException {
        for (Client c : clients) {
            System.out.println(c);
        }
        inputStr = br.readLine();
    }

    private static void buyTicket() throws IOException {
        System.out.println("please input a client!");
        inputStr = br.readLine();

        Client findClient = null;
        for (Client client : clients) {
            String clientName = client.getFirstName() + " " + client.getLastName();
            if (StringUtils.equalsIgnoreCase(clientName, inputStr)) {
                findClient = client;
                break;
            }
        }

        if (null == findClient) {
            System.out.println("the input client is invalid, choose the operation from menu!");
            inputStr = br.readLine();
        } else if (findClient.getActivities().size() >= 3) {
            System.out.println("one client can buy no more than three tickets, choose the operation from menu!");
            inputStr = br.readLine();
        } else {
            System.out.println("please input a activity!");
            inputStr = br.readLine();

            boolean validActivity = false;
            for (Activity activity : activities) {
                if (StringUtils.equalsIgnoreCase(activity.getActivityName(), inputStr)) {
                    validActivity = true;
                    break;
                }
            }

            if (!validActivity) {
                System.out.println("the input activity is invalid, choose the operation from menu!");
                inputStr = br.readLine();
            } else {
                boolean duplicateBuy = false;
                for (Activity activity : findClient.getActivities()) {
                    if (StringUtils.equalsIgnoreCase(activity.getActivityName(), inputStr)) {
                        duplicateBuy = true;
                        break;
                    }
                }

                if (duplicateBuy) {
                    System.out.println("one client can only buy one ticket for each activity, choose the operation from menu!");
                    inputStr = br.readLine();
                } else {
                    Activity findActivity = null;
                    for (Activity activity : activities) {
                        if (StringUtils.equalsIgnoreCase(activity.getActivityName(), inputStr)) {
                            findActivity = activity;
                            break;
                        }
                    }

                    if (findActivity.getRemainNum() <= 0) {
                        FileUtils.writeStringToFile(new File(OUTPUT_FILE_PATH), "there is no enough ticket!");
                        System.out.println("there is no enough ticket, choose the operation from menu!");
                        inputStr = br.readLine();
                    } else {
                        findClient.getActivities().add(findActivity);
                        findActivity.setRemainNum(findActivity.getRemainNum() - 1);
                        System.out.println("buy ticket successful!");
                        inputStr = br.readLine();
                    }
                }
            }
        }
    }

    private static void cancelTicket() throws IOException {
        System.out.println("please input a client!");
        inputStr = br.readLine();

        Client findClient = null;
        for (Client client : clients) {
            String clientName = client.getFirstName() + " " + client.getLastName();
            if (StringUtils.equalsIgnoreCase(clientName, inputStr)) {
                findClient = client;
                break;
            }
        }

        if (null == findClient) {
            System.out.println("the input client is invalid, choose the operation from menu!");
            inputStr = br.readLine();
        } else {
            System.out.println("please input a activity!");
            inputStr = br.readLine();

            boolean validActivity = false;
            for (Activity activity : activities) {
                if (StringUtils.equalsIgnoreCase(activity.getActivityName(), inputStr)) {
                    validActivity = true;
                    break;
                }
            }

            if (!validActivity) {
                System.out.println("the input activity is invalid, choose the operation from menu!");
                inputStr = br.readLine();
            } else {
                boolean haveBuyTicket = false;
                Iterator<Activity> clientActivityIt = findClient.getActivities().iterator();
                while (clientActivityIt.hasNext()) {
                    Activity a = clientActivityIt.next();
                    if (StringUtils.equalsIgnoreCase(a.getActivityName(), inputStr)) {
                        clientActivityIt.remove();
                        haveBuyTicket = true;
                        break;
                    }
                }

                if (!haveBuyTicket) {
                    System.out.println("the client not have this activity ticket, choose the operation from menu!");
                } else {
                    for (Activity a : activities) {
                        if (StringUtils.equalsIgnoreCase(a.getActivityName(), inputStr)) {
                            a.setRemainNum(a.getRemainNum() + 1);
                        }
                    }

                    System.out.println("cancel ticket successful!");
                }
                inputStr = br.readLine();
            }
        }
    }
}