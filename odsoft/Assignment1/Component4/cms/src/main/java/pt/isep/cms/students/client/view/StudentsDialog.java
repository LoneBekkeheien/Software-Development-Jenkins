/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.students.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.students.client.presenter.EditStudentPresenter;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Dialog Box for Adding and Updating Contacts.
 */
public class StudentsDialog implements EditStudentPresenter.Display {
	
	public enum Type {
		ADD, 
		UPDATE
	}
	
	/**
	 * The constants used in this Content Widget.
	 */
	public static interface CwConstants extends Constants {
		
		String cwAddStudentDialogCaption();
		
		String cwUpdateStudentDialogCaption();
				
//		String cwDialogBoxClose();
//
//		String cwDialogBoxDescription();
//
//		String cwDialogBoxDetails();
//
//		String cwDialogBoxItem();
//
//		String cwDialogBoxListBoxInfo();
//
//		String cwDialogBoxMakeTransparent();
//
//		String cwDialogBoxName();
//
//		String cwDialogBoxShowButton();
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;
	
	// Widgets
	private final TextBox firstName;
	private final TextBox lastName;
	private final TextBox gender; //new
	private final TextBox birthDate; //new
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Firstname"));
		detailsTable.setWidget(0, 1, firstName);
		detailsTable.setWidget(1, 0, new Label("Lastname"));
		detailsTable.setWidget(1, 1, lastName);
		detailsTable.setWidget(2, 0, new Label("Gender")); //new
		detailsTable.setWidget(2, 1, gender); //new
		detailsTable.setWidget(3, 0, new Label("Birthdate")); //new
		detailsTable.setWidget(3, 1, birthDate); //new
		firstName.setFocus(true);
	}

	DecoratorPanel contentDetailsDecorator;
	final DialogBox dialogBox;

	/**
	 * Constructor.
	 *
	 * @param constants
	 *            the constants
	 */
	public StudentsDialog(ShowcaseConstants constants, Type type) {
		// super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

		this.constants = constants; 
		this.globalConstants = constants;

		// Init the widgets of the dialog
		contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("30em"); // em = size of current font
		// initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		
		// Create the students list
		//
		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("students-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-student-input");
		firstName = new TextBox();
		lastName = new TextBox();
		gender = new TextBox(); //new
		birthDate = new TextBox(); //new
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save"); 
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

		dialogBox = new DialogBox();
		dialogBox.ensureDebugId("cwDialogBox");
		if (type==Type.ADD)
			dialogBox.setText(constants.cwAddStudentDialogCaption());
		else 
			dialogBox.setText(constants.cwUpdateStudentDialogCaption());
			
		dialogBox.add(contentDetailsDecorator);

		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
	}

	public void displayDialog() {
		// Create the dialog box
		// final DialogBox dialogBox = createDialogBox();

		dialogBox.center();
		dialogBox.show();
	}

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return saveButton;
		// return null;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return cancelButton;
		// return null;
	}

	@Override
	public HasValue<String> getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
		// return null;
	}

	@Override
	public HasValue<String> getLastName() {
		// TODO Auto-generated method stub
		return lastName;
		// return null;
	}

	//new
	@Override
	public HasValue<String> getGender() {
		// TODO Auto-generated method stub
		return gender; 
		// return null;
	}
	
	//new
	@Override
	public HasValue<String> getBirthDate() {
		// TODO Auto-generated method stub
		return birthDate;
		// return null;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// return null;
		displayDialog();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		// return null;
		dialogBox.hide();
	}

}
