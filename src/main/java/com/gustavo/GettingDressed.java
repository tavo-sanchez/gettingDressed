package com.gustavo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GettingDressed {

    private static final String HOT = "HOT";
    private static final String COLD = "COLD";
    private static final String SPACE = " ";
    private static final String FAIL = "fail";
    private static final String COMMA = ",";
    private static final String SOCKS = "3";
    private static final String JACKET = "5";
    private static final String FOOTWEAR = "1";
    private static final String PANTS = "6";
    private static final String HEADWEAR = "2";
    private static final String SHIRT = "4";
    private static final String LEAVE = "7";
    private static HashMap<String, MyCommand> commandsHashMap;

    /**
     * This method get you fully dressed and leave the house
     * @param temperature
     * @param commands
     * @return String output
     */
    public static String dress(String temperature, String commands){

        StringBuilder output = new StringBuilder();
        List<String> commandUsed = new ArrayList<>();
        boolean isSuccess = false;

        temperature = temperature.toUpperCase();
        commandsHashMap = initCommands();
        commands = commands.replace(" ", "");
        String[] commandArray = commands.split(COMMA);

        if(HOT.equals(temperature) || COLD.equals(temperature)){
            for(String command: commandArray) {

                // No valid command
                if (!commandsHashMap.containsKey(command)) {
                    output = failed(output, commandUsed, temperature);
                    isSuccess = false;
                    break;
                }

                // Validate if is hot and is trying to wear socks or jacket
                if(ifSocks(temperature, command) ||
                        ifJacket(temperature,command) ){
                    output = failed(output, commandUsed, temperature);
                    isSuccess = false;
                    break;
                }

                if((COLD.equals(temperature) && !ifSocksBeforeFootwear(command, commandUsed)) ||
                        !ifPantsBeforeFootwear(command, commandUsed)){
                    output = failed(output, commandUsed, temperature);
                    isSuccess = false;
                    break;
                }

                if(!ifShirtBeforeHeadwear(command, commandUsed) ||
                        !ifShirtBeforeJacket(command, commandUsed)){
                    output = failed(output, commandUsed, temperature);
                    isSuccess = false;
                    break;
                }

                if(!commandUsed.contains(command)){
                    if (commandUsed.isEmpty()) {
                        // Validate first command: Removing PJs
                        if (Integer.parseInt(command) != 8) {
                            output = failed(output, commandUsed, temperature);
                            isSuccess = false;
                            break;
                        }else {
                            commandUsed.add(command);
                            isSuccess = true;
                        }
                    }else {
                        commandUsed.add(command);
                        isSuccess = true;
                    }
                }else {
                    output = failed(output, commandUsed, temperature);
                    isSuccess = false;
                    break;
                }
            }
            if(isSuccess){
                if(COLD.equals(temperature) && commandArray.length == 8){
                    if(isSevenLastElement(commandArray)){
                        output = getOutput(output, commandUsed, temperature);
                    }else {
                        output = failed(output, commandUsed, temperature);
                    }
                }else if(HOT.equals(temperature) && commandArray.length == 6){
                    if(isSevenLastElement(commandArray)){
                        output = getOutput(output, commandUsed, temperature);
                    }else {
                        output = failed(output, commandUsed, temperature);
                    }
                }else if(isSevenLastElement(commandArray)){
                        commandUsed.remove(commandUsed.size()-1);
                        output = failed(output, commandUsed, temperature);

                }else {
                    output = failed(output, commandUsed, temperature);
                }

            }
        }else {
            output.append("No valid option for temperature: " + temperature);
        }

        return output.toString();

    }

    /**
     * Init valid commands with their descriptions
     * @return HashMap with valid commands as Key and object MyCommand with description
     */
    public static HashMap<String, MyCommand> initCommands(){

        HashMap<String, MyCommand> myCommandsHashMap = new HashMap<String, MyCommand>();

        MyCommand command1 = new MyCommand("Put on footwear", "sandals", "boots");
        MyCommand command2 = new MyCommand("Put on headwear", "sunglasses", "hat");
        MyCommand command3 = new MyCommand("Put on socks", "", "socks");
        MyCommand command4 = new MyCommand("Put on shirt", "shirt", "shirt");
        MyCommand command5 = new MyCommand("Put on jacket", "", "jacket");
        MyCommand command6 = new MyCommand("Put on pants", "shorts", "pants");
        MyCommand command7 = new MyCommand("Leave house", "leaving house", "leaving house");
        MyCommand command8 = new MyCommand("Take off pajamas", "Removing PJs", "Removing PJs");

        myCommandsHashMap.put("1", command1);
        myCommandsHashMap.put("2", command2);
        myCommandsHashMap.put("3", command3);
        myCommandsHashMap.put("4", command4);
        myCommandsHashMap.put("5", command5);
        myCommandsHashMap.put("6", command6);
        myCommandsHashMap.put("7", command7);
        myCommandsHashMap.put("8", command8);

        return myCommandsHashMap;

    }

    /**
     * This method build the failed output
     * @param output
     * @param commandList
     * @param temperature
     * @return
     */
    public static StringBuilder failed(StringBuilder output, List<String> commandList, String temperature){

        if(commandList.isEmpty()){
            output.append(FAIL);
        }else{
            for(String command: commandList){
                MyCommand currentCommand = commandsHashMap.get(command);
                if(output.length() == 0) {
                    output.append(currentCommand.getResponse(temperature));
                }else {
                    output.append(COMMA);
                    output.append(SPACE);
                    output.append(currentCommand.getResponse(temperature));
                }
            }
            output.append(COMMA);
            output.append(SPACE);
            output.append(FAIL);
        }

        return output;
    }

    /**
     * Method that build the correct output
     * @param output
     * @param commandList
     * @param temperature
     * @return
     */
    public static StringBuilder getOutput(StringBuilder output, List<String> commandList, String temperature){

        for(String command: commandList){
            MyCommand currentCommand = commandsHashMap.get(command);
            if(output.length() == 0){
                output.append(currentCommand.getResponse(temperature));
            }else {
                output.append(COMMA);
                output.append(SPACE);
                output.append(currentCommand.getResponse(temperature));
            }
        }
        return output;
    }

    /**
     * Check for socks when is hot
     * @param temperature
     * @param command
     * @return
     */
    public static boolean ifSocks(String temperature, String command){
        return (HOT.equals(temperature) && SOCKS.equals(command));
    }

    /**
     * Check for jacket when is hot
     * @param temperature
     * @param command
     * @return
     */
    public static boolean ifJacket(String temperature, String command){
        return (HOT.equals(temperature) && JACKET.equals(command));
    }

    /**
     * Validate socks must be put on before footwear
     * @param command
     * @param commandList
     * @return
     */
    public static boolean ifSocksBeforeFootwear(String command, List<String> commandList) {
        return !FOOTWEAR.equals(command) || commandList.contains(SOCKS);
    }

    /**
     * Validate Pants must be put on before footwear
     * @param command
     * @param commandList
     * @return
     */
    public static boolean ifPantsBeforeFootwear(String command, List<String> commandList) {
        return !FOOTWEAR.equals(command) || commandList.contains(PANTS);
    }

    /**
     * Validate shirt must be put on before the headwear
     * @param command
     * @param commandList
     * @return
     */
    public static boolean ifShirtBeforeHeadwear(String command, List<String> commandList){
        return !HEADWEAR.equals(command) || commandList.contains(SHIRT);
    }

    /**
     * Validate shirt must be put on before the jacket
     * @param command
     * @param commandList
     * @return
     */
    public static boolean ifShirtBeforeJacket(String command, List<String> commandList){
        return !JACKET.equals(command) || commandList.contains(SHIRT);
    }

    /**
     * Validate if the last element is 7
     * @param commandArray
     * @return
     */
    public static boolean isSevenLastElement(String[] commandArray){
        return commandArray[commandArray.length-1].equals(LEAVE);
    }
}
