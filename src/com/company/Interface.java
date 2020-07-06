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

public class Interface extends JFrame {
    // Модель данных таблицы
    private DefaultTableModel tableModel;
    private JTable table1;
    private int number = 1;
    private int columnHelp = 0;

    // Данные для таблиц
    // Заголовки столбцов
    private void removeColumn(int index, JTable myTable) {
        index = tableModel.getColumnCount();
        System.out.println(index);
        int nRow = myTable.getRowCount();
        int nCol = myTable.getColumnCount() - 1;
        Object[][] cells = new Object[nRow][nCol];
        String[] names = new String[nCol];

        for (int j = 0; j < nCol; j++) {
            if (j < index) {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j);
                }
            } else {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j + 1);
                }
            }
        }

        tableModel = new DefaultTableModel(cells, names);
        myTable.setModel(tableModel);
        number--;
        columnHelp--;
    }

    public Interface(String[] args) {
        super("Пример использования TableModel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        // Определение столбцов
        // Наполнение модели данными
        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);
        table1.setRowHeight(210);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setColumnSelectionAllowed(true);


        // Создание кнопки добавления строки таблицы
        JButton TakeFromFile = new JButton("Из txt");
        TakeFromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer[][] dv;
                    JFileChooser jfc = new JFileChooser(".");
                    jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                    if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                        return;
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    Scanner scanner = null;
                    scanner = new Scanner(file);
                    String s;
                    ArrayList<Integer[]> kek = new ArrayList<>();
                    ArrayList<Integer> ama1 = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        s = scanner.nextLine();
                        String lol[] = s.split(" ");
                        for (int i = 0; i < lol.length; i++) {
                            ama1.add(Integer.parseInt(lol[i]));
                        }
                        Integer[] array1 = new Integer[ama1.size()];
                        ama1.toArray(array1);
                        kek.add(array1);
                        ama1.clear();
                    }
                    dv = new Integer[kek.size()][kek.get(0).length];
                    for (int i = 0; i < kek.size(); i++) {
                        for (int j = 0; j < kek.get(0).length; j++) {
                            dv[i][j] = kek.get(i)[j];
                        }

                    }
                    for (int g = 0; g < dv.length; g++) {
                        if (table1.getRowCount() == 0) {
                            Integer lol2[] = new Integer[table1.getRowCount()];
                            for (int i = 0; i < lol2.length; i++) {
                                lol2[i] = 0;
                            }
                            tableModel.addColumn("#" + number, lol2);
                            number++;
                        }
// Вставка новой строки после выделенной
                        String[] lol = new String[table1.getRowCount() + 1];
                        for (int i = 0; i < lol.length; i++) {
                            lol[i] = "0";
                        }
                        tableModel.addRow(lol);
                    }
                    for (int k = 0; k < dv[0].length - 1; k++) {
                        Integer lol2[] = new Integer[table1.getRowCount()];
                        for (int i = 0; i < lol2.length; i++) {
                            lol2[i] = 0;
                        }
                        tableModel.addColumn("#" + number, lol2);
                        number++;
                    }
                    for (int g = 0; g < dv.length; g++) {
                        for (int f = 0; f < dv[0].length; f++) {
                            tableModel.setValueAt(dv[g][f], g, f);
                        }
                    }
                } catch (Exception v) {
                    System.out.println("Введите нормальное имя");
                }
            }
        });
        JButton AddColumn = new JButton("Добавить столбец");
        AddColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                if (table1.getRowCount() == 0) {
                    Integer lol3[] = new Integer[table1.getRowCount()];
                    for (int i = 0; i < lol3.length; i++) {
                        lol3[i] = 0;
                    }
                    tableModel.addRow(lol3);
                }
                int idx = table1.getSelectedRow();
                Integer lol2[] = new Integer[table1.getRowCount()];
                for (int i = 0; i < lol2.length; i++) {
                    lol2[i] = 0;
                }
                tableModel.addColumn("#" + Integer.toString(number));
                tableModel.setValueAt(number,0,columnHelp);
                columnHelp++;
                number++;
            }
        });
        JTextField Read = new JTextField(2);
        JButton Calculate = new JButton("Вычислить");
        Calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                    for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                        dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                    }
                }
                ArrayList<Integer> arr = new ArrayList<>();
                for (int i = 0; i < dv2[0].length; i++) {
                    arr.add(dv2[0][i]);
                }
                DoublyLinkedListImpl dll = new DoublyLinkedListImpl();
                for (int i = 0; i < arr.size(); i++) {
                    dll.addAsQu(arr.get(i));
                }
                String lol = Read.getText();
                dll.Solution(table1.getColumnCount(),Integer.parseInt(lol));
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton Remove = new JButton("Удалить");
        Remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                // Удаление выделенной строки
                removeColumn(table1.getSelectedColumn(), table1);
            }
        });
        // Создание таблицы на основе модели данных
        // Определение высоты строки

        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);

        JPanel buttons = new JPanel();
        buttons.add(AddColumn);
        buttons.add(TakeFromFile);
        buttons.add(Calculate);
        buttons.add(Read);
        buttons.add(Remove);
        getContentPane().add(buttons, "South");
        // Вывод окна на экран
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}