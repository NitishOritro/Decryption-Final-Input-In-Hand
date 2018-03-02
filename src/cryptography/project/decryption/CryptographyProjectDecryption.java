/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography.project.decryption;

import com.letter.cryptography.LetterInNumber;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Nitish Ranjan Bhowmik
 */
public class CryptographyProjectDecryption 
{
    public static void main(String[] args) throws IOException 
    {
        Scanner input = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cipherText;
        System.out.println("Enter Your Ciphertext");
        cipherText = br.readLine();
        
        System.out.println("Enter length of padding Z");
        int length = input.nextInt();
        
        int len = cipherText.length() - length;
        
        int i,j,k,randomNumber,row=0,column=16;
        row = cipherText.length() / 16;
        char ciphertextRotor [][] = new char [row][column];
        int pointer = 0;
        
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                    
                if(pointer < cipherText.length() && cipherText.charAt(pointer) != ' ')
                {
                    ciphertextRotor [i][j] = cipherText.charAt(pointer);
                    pointer++;
                }
                else 
                {
                    break;
                }
            }
            pointer++;
            
        }
        
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                if(Character.isLetter(ciphertextRotor[i][j]) && ciphertextRotor[i][j] != ' ')
                {
                    LetterInNumber cipherInNumber = new LetterInNumber(ciphertextRotor[i][j]);
                    
                    int cipherValue =  (cipherInNumber.getNumber() - i) % 26;       //(c-rowNum) mod 26
                    if(cipherValue < 0)
                    {
                        cipherValue = cipherValue + 26;
                    }    
                    if(cipherValue == 0)
                    {
                        cipherValue = 26;
                    }
                    LetterInNumber cipherInLetter = new LetterInNumber(cipherValue);
                    if(Character.isLowerCase(ciphertextRotor[i][j]))
                    {
                        
                        ciphertextRotor[i][j] = Character.toLowerCase(cipherInLetter.getLetter());
                        
                    }
                    else
                    {
                        ciphertextRotor[i][j] = cipherInLetter.getLetter();
                    }
                }
            }
        }
        
        String CipherTextToPlaintext = "";
        String generatePlaintext = "";
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                CipherTextToPlaintext = CipherTextToPlaintext + ciphertextRotor [i][j];
            }
            CipherTextToPlaintext = CipherTextToPlaintext + ' ';
        }
        
        
        
        String correctionCipherTextToPlaintext = "";
        
        for(i=0;i<CipherTextToPlaintext.length();i++)
        {
            if(CipherTextToPlaintext.charAt(i) != ' ')
            {
                correctionCipherTextToPlaintext = correctionCipherTextToPlaintext + CipherTextToPlaintext.charAt(i);
            }
        }
        
        int exactLength = correctionCipherTextToPlaintext.length() - length;
        
        pointer = 0;
        String exactPlaintext = "";
        int padding = exactLength % 16;
        for(i=0;i<column;i++)
        {
            for(j=0;j<row;j++)
            {
                if(pointer != correctionCipherTextToPlaintext.length())
                {
                    if(j == row-1 && i >= padding)
                    {
                        ciphertextRotor [j][i] = 'X';
                    }
                    else
                    {
                        exactPlaintext = exactPlaintext + ciphertextRotor[j][i];
                        pointer++;   
                    }
                }
            }
        }
        
        System.out.println("Three Random Possible of Outputs are");
        for(randomNumber=1;randomNumber<=3;randomNumber++)
        {
            Random rand = new Random();
            int n = rand.nextInt(3) + 3;
            for(i=0;i<exactPlaintext.length();i++)
            {
                if(Character.isLetter(exactPlaintext.charAt(i)) && exactPlaintext.charAt(i) != ' ')
                {
                    LetterInNumber letterInNumber = new LetterInNumber(exactPlaintext.charAt(i));
                    int cipherValue =  (letterInNumber.getNumber() - n) % 26;       //(p+n) mod 26

                    if(cipherValue == 0)
                    {
                        cipherValue = 26;
                    }
                    if(cipherValue < 0)
                    {
                        cipherValue = cipherValue + 26;
                    }

                    LetterInNumber numberInLetter = new LetterInNumber(cipherValue);

                    if(Character.isLowerCase(exactPlaintext.charAt(i)))
                    {
                        char cipherCharacter = numberInLetter.getLetter();
                        cipherCharacter = Character.toLowerCase(cipherCharacter);
                        generatePlaintext = generatePlaintext + cipherCharacter;
                    }
                    else
                    {
                        char cipherCharacter = numberInLetter.getLetter();
                        generatePlaintext = generatePlaintext + cipherCharacter;
                    }
                }
                else if(exactPlaintext.charAt(i) != ' ' || Character.isWhitespace(exactPlaintext.charAt(i)))
                {
                    generatePlaintext = generatePlaintext + exactPlaintext.charAt(i);
                }
            }
        System.out.println("\nWhene Key is "+ n +"\nGenerate Final Plaintext is : " +generatePlaintext);
        generatePlaintext = "";
        }

    }    
}
