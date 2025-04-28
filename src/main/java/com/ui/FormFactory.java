package com.ui;

/**
 * This is an interface, it means a complex type.
 * It contains a method that creates a form from
 * an arrayList
 */

public interface FormFactory<T> {
  void createForm (TableModel tableModel, T data);

  void createForm (TableModel tableModel);
}
