/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letter.cryptography;
/**
 *
 * @author Nitish Ranjan Bhownmik
 */
public class LetterInNumber 
{
    char letter;
    int number;
    
    public LetterInNumber(char letter) 
    {
        int letterAsciiValue = (int) letter;
        letterAsciiValue = letterAsciiValue - 64;
        this.number = letterAsciiValue;
    }
    
    public LetterInNumber(int number) 
    {
        int letterAsciiValue = number + 64;
        char asciiValueToCharacter = (char) letterAsciiValue;
        this.letter = asciiValueToCharacter;
    }
  
    public char getLetter() 
    {
        return letter;
    }

    public void setLetter(char letter) 
    {
        this.letter = letter;
    }

    public int getNumber() 
    {
        return number;
    }

    public void setNumber(int number) 
    {
        this.number = number;
    }

}
