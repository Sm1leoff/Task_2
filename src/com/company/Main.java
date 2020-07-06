package com.company;

import  javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) throws FileNotFoundException {
        String vs[] = new String[args.length];
        for (int v = 0; v < args.length; v++) {
            vs[v] = args[v];
        }
        new Interface(vs);
    }
}