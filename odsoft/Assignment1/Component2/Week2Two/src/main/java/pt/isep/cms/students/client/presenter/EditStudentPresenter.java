package pt.isep.cms.students.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.students.client.StudentsServiceAsync;
import pt.isep.cms.students.client.event.StudentUpdatedEvent;
import pt.isep.cms.students.client.event.EditStudentCancelledEvent;
import pt.isep.cms.students.shared.Student;

public class EditStudentPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getLastName();

		HasValue<String> getEmailAddress();

		void show();

		void hide();
	}

	private Student contact;
	private final StudentsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditStudentPresenter(StudentsServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.contact = new Student();
		this.display = display;
		bind();
	}

	public EditStudentPresenter(StudentsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getStudent(id, new AsyncCallback<Student>() {
			public void onSuccess(Student result) {
				contact = result;
				EditStudentPresenter.this.display.getFirstName().setValue(contact.getFirstName());
				EditStudentPresenter.this.display.getLastName().setValue(contact.getLastName());
				EditStudentPresenter.this.display.getEmailAddress().setValue(contact.getEmailAddress());
				// HER MÅ DU SKRIVA RESTEN!!!
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving contact");
			}
		});

	}

	public void bind() {
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doSave();
				display.hide();
			}
		});

		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.hide();
				eventBus.fireEvent(new EditStudentCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		contact.setFirstName(display.getFirstName().getValue());
		contact.setLastName(display.getLastName().getValue());
		contact.setEmailAddress(display.getEmailAddress().getValue());
		// HER MÅ DU LAGRA RESTEN

		if (contact.getId() == null) {
			// Adding new contact
			rpcService.addStudent(contact, new AsyncCallback<Student>() {
				public void onSuccess(Student result) {
					eventBus.fireEvent(new StudentUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding contact");
				}
			});
		} else {
			// updating existing contact
			rpcService.updateStudent(contact, new AsyncCallback<Student>() {
				public void onSuccess(Student result) {
					eventBus.fireEvent(new StudentUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating contact");
				}
			});
		}
	}

}
