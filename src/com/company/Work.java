package com.company;

import javax.swing.*;
import java.util.NoSuchElementException;

class DoublyLinkedListImpl
{
    ClassNode head;
    ClassNode tail; // только для очереди


    public DoublyLinkedListImpl()
    {
        head = null;
    }

    void addAsQu(int elem)
    // добавление нового элемента в конец списка (как при создании очереди)
    {
        if (head == null) // создание первого элемента
        {
            head = new ClassNode(elem);
            tail = head;
        }
        else // добавление элемента в конец очереди
        {
            tail.next= new ClassNode(elem);
            tail = tail.next;
            tail.next = head;
        }
    }

    //addAsQu// printList
    private void FillOut (int N){
        int num=1;
        for (int i=0;i<N;i++){
            addAsQu(num);
            num++;
        }
    }
    private void Remove (ClassNode p){
        ClassNode k = head;
        while (k.next!=p)
        {
            k = k.next;
        }
        if (k==k.next.next){
            k.next=null;
        }else {
            if (k.next==head){
                k.next=k.next.next;
                head=k.next;
            }else {
                k.next=k.next.next;
            }
        }
    }
    public void Solution (int N,int k){
        if (k>N){
            JOptionPane.showMessageDialog(null, "Номер удаляемого не может быть больше количества");
            return;
        }
        FillOut(N);
        int count=1;
        if (head == null){
            JOptionPane.showMessageDialog(null, "Список пуст");
        }
        else
        {
            ClassNode p = head;
            while (p != null)
            {
                if (p.next==null){
                    JOptionPane.showMessageDialog(null, "Номер последнего удаленного: "+ p.info);
                    return;
                }
                if (count%k==0){
                    Remove(p);
                }
                count++;
                p = p.next;
            }
        }
    }
}


/// <summary>
/// ///////////////////////////////////////////////////////
/// </summary>

class ClassNode
{
    public int info;
    public ClassNode next;

    public ClassNode(int aInfo) // конструктор
    {
        info = aInfo;
        next = null;
    }
}