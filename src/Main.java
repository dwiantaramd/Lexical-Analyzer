/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MSI
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Token List
        List<String> Token = new ArrayList();
        
        //Input
        Scanner StrScan = new Scanner(System.in);
        System.out.print("Input : ");
        String Formula = StrScan.nextLine();
        
        //Change String to array of char
        char[] Lexic = Formula.toCharArray();
        
        //Phase 1
        // (0) = Error
        for(int idx = 0; idx < Formula.length(); idx++){
            //Proposisi(1)
            if(Lexic[idx] == 'p' || Lexic[idx]== 'q' || Lexic[idx] == 'r' || Lexic[idx] == 's'){
                idx++;
                if(idx != Formula.length()){
                    if (Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                        Token.add("1");
                    } else {
                        Token.add("0");
                    }
                } else {
                    Token.add("1");
                }
            }
            //NOT(2)
            else if(Lexic[idx] == 'n'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'o'){
                    idx++;
                    if(idx != Formula.length() && Lexic[idx] == 't'){
                        idx++;
                        if(idx != Formula.length()){
                            if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                                Token.add("2");
                            } else {
                                Token.add("0");
                            }
                        } else {
                            Token.add("2");
                        }
                    } else {
                        Token.add("0");
                    }
                } else {
                    Token.add("0");
                }
            }
            //AND(3)
            else if(Lexic[idx] == 'a'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'n'){
                    idx++;
                    if(idx != Formula.length() && Lexic[idx] == 'd'){
                        idx++;
                        if(idx != Formula.length()){
                            if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                                Token.add("3");
                            } else {
                                Token.add("0");
                            }
                        } else {
                            Token.add("3");
                        }
                    } else {
                        Token.add("0");
                    }
                } else {
                    Token.add("0");
                }
            }
            //OR(4)
            else if(Lexic[idx] == 'o'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'r'){
                    idx++;
                    if(idx != Formula.length()){
                        if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                            Token.add("4");
                        } else {
                            Token.add("0");
                        }
                    } else {
                        Token.add("4");
                    }
                } else {
                    Token.add("0");
                }
            }
            //XOR(5)
            else if(Lexic[idx] == 'x'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'o'){
                    idx++;
                    if(idx != Formula.length() && Lexic[idx] == 'r'){
                        idx++;
                        if(idx != Formula.length()){
                            if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                                Token.add("5");
                            } else {
                                Token.add("0");
                            }
                        } else {
                            Token.add("5");
                        }
                    } else {
                        Token.add("0");
                    }
                } else {
                    Token.add("0");
                }
            }
            //IF and IFF (6) and (8)
            else if(Lexic[idx] == 'i'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'f'){
                    idx++;
                    if(idx != Formula.length() && Lexic[idx] == 'f'){
                        idx++;
                        if(idx != Formula.length()){
                            if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                                Token.add("8");
                            } else {
                                Token.add("0");
                            }
                        } else {
                            Token.add("8");
                        }
                    } else if (idx != Formula.length()){
                        if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                            Token.add("6");
                        } else {
                            Token.add("0");
                        }
                    } else {
                        Token.add("6");
                    }
                } else {
                    Token.add("0");
                }
            }
            //THEN (7)
            else if(Lexic[idx] == 't'){
                idx++;
                if(idx != Formula.length() && Lexic[idx] == 'h'){
                    idx++;
                    if(idx != Formula.length() && Lexic[idx] == 'e'){
                        idx++;
                        if(idx != Formula.length() && Lexic[idx] == 'n'){
                            idx++;
                            if(idx != Formula.length()){
                                if(Lexic[idx] == ' ' || Lexic[idx] == ')' || Lexic[idx] == '('){
                                    Token.add("7");
                                } else {
                                    Token.add("0");
                                }
                            } else {
                                Token.add("7");
                            }
                        } else {
                            Token.add("0");
                        }
                    } else {
                        Token.add("0");
                    }
                } else {
                    Token.add("0");
                }
            
            }
            //Anything else except space
            else if(Lexic[idx] != ' ' && Lexic[idx] != ')' && Lexic[idx] != '(') {
                Token.add("0");
            }
            //(Grouping Symbol) (9) and (10)
            if(idx != Formula.length() && Lexic[idx] == ')'){
                Token.add("10");
            } else if (idx != Formula.length() && Lexic[idx] == '('){
                Token.add("9");
            }
        }
        
        //Output Token
        System.out.print("Token : ");
        for(String x : Token){
            if(x.equals("0")){
                System.out.print("Error ");
            } else{
                System.out.print(x + " ");
            }
        }
        
        //Phase 2
        List<String> stack = new ArrayList<>();
        boolean error = false;
        stack.add("#");
        int idx = 0;
        while(idx < Token.size() && !error){
            switch(Token.get(idx)){
                //error
                case "0" :
                    error = true;
                    break;
                //proposisi
                case "1" :
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("3") || Token.get(idx).equals("4") || Token.get(idx).equals("5") || Token.get(idx).equals("8")){
                            idx++;
                            if(idx != Token.size()){
                                if(Token.get(idx).equals("2")||Token.get(idx).equals("3") || Token.get(idx).equals("4") || Token.get(idx).equals("5") || Token.get(idx).equals("8")){
                                    error = true;
                                }
                            }else{
                                error = true;
                            }
                        }else if(!Token.get(idx).equals("10") && !Token.get(idx).equals("7")){
                            error = true;
                        }
                    } else if(stack.size() == 1 && stack.get(0).equals("#")){
                        stack.clear();
                    }
                    break;
                //not    
                case "2" :
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("1")){
                            idx++;
                            if(idx != Token.size() && !Token.get(idx).equals("10")){
                                error = true;
                            }
                        } else if (Token.get(idx).equals("9")){
                            stack.add("9");
                            idx++;
                        } 
                    } else {
                        error = true;
                    }
                    break;
                //and
                case "3" : 
                    if(idx == 0){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if (Token.get(idx).equals("1")){
                            idx++;
                        } else if (Token.get(idx).equals("9")){
                            stack.add("9");
                            idx++;
                        } else {
                            error = true;
                        }
                    } else {
                        error = true;
                    }
                    break;
                //or
                case "4" :
                    if(idx == 0){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("2")){
                            idx++;
                        } else if (Token.get(idx).equals("1")){
                            idx++;
                        } else if (Token.get(idx).equals("9")){
                            stack.add("9");
                            idx++;
                        } else {
                            error = true;
                        }
                    } else {
                        error = true;
                    }
                    break;
                //xor
                case "5" :
                    if(idx == 0){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("2")){
                            idx++;
                        } else if (Token.get(idx).equals("1")){
                            idx++;
                        } else if (Token.get(idx).equals("9")){
                            stack.add("9");
                            idx++;
                        } else {
                            error = true;
                        }
                    } else {
                        error = true;
                    }
                    break;
                //if
                case "6" :
                    stack.add("6");
                    idx++;
                    if(idx != Token.size()){
                        if(!Token.get(idx).equals("9") && !Token.get(idx).equals("1")){
                            error = true;
                        } else if (Token.get(idx).equals("1")){
                            idx++;
                            if(idx != Token.size() && !Token.get(idx).equals("7")){
                                error = true;
                            }
                        }
                    } else {
                        error = true;
                    }
                    break;
                //then
                case "7" :
                    boolean foundIF = false;
                    if(stack.get(stack.size()-1).equals("6")){
                        foundIF = true;
                        stack.remove(stack.size()-1);
                    } else if(stack.get(stack.size()-1).equals("#")){
                        stack.clear();  
                    }
                    
                    if(!foundIF){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("2") || Token.get(idx).equals("3") || Token.get(idx).equals("4") || Token.get(idx).equals("5") || Token.get(idx).equals("8")){
                            error = true;
                        } else if(Token.get(idx).equals("1")){
                            idx++;
                            if(idx != Token.size() && !Token.get(idx).equals("10")){
                                error = true;
                            }
                        }
                    } else {
                        error = true;
                    }
                    break;
                //iff
                case "8" :
                    if(idx == 0){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("2")){
                            idx++;
                        } else if (Token.get(idx).equals("1")){
                            idx++;
                        } else if (Token.get(idx).equals("9")){
                            stack.add("9");
                            idx++;
                        } else {
                            error = true;
                        }
                    } else {
                        error = true;
                    }   
                    break;
                // "("
                case "9" :
                    stack.add("9");
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("3") || Token.get(idx).equals("4") || Token.get(idx).equals("5") || Token.get(idx).equals("8") || Token.get(idx).equals("10")){
                            error = true;
                        }
                    } else {
                        error = true;
                    }
                    break;
                // ")"
                case "10" :
                    boolean foundKurbuka = false;
                    if(stack.get(stack.size()-1).equals("9")){
                        foundKurbuka = true;
                        stack.remove(stack.size()-1);
                    } else if(stack.get(stack.size()-1).equals("#")){
                        stack.clear();
                    }
                    if(!foundKurbuka){
                        error = true;
                    }
                    idx++;
                    if(idx != Token.size()){
                        if(Token.get(idx).equals("1") || Token.get(idx).equals("2") || Token.get(idx).equals("6")){
                            error = true;
                        }
                    }
                    break;
                default :
                    break;
            }   
        }
        if(!stack.isEmpty() && stack.size() == 1 && stack.get(0).equals("#")){
            stack.clear();
        }
        //Output Phase 2
        System.out.println("");
        if(stack.isEmpty() && !error){
            System.out.println("Valid");
        } else {
            System.out.println("Tidak Valid");
        }
        
    }
}