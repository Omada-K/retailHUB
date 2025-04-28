package com.formFactory;

import com.model.User;
import com.ui.FormFactory;
import com.ui.TableModel;
import com.ui.UserForm;

public class UserFormFactory implements FormFactory<User> {

  @Override
  public void createForm (TableModel model, User data) {
    new UserForm(model, data);
  }

  @Override
  public void createForm (TableModel model) {
    new UserForm(model);
  }
}