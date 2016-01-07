package com.incra.tutorial;

import com.incra.tutorial.AddressBookProtos.AddressBook;
import com.incra.tutorial.AddressBookProtos.Person;

import java.io.FileInputStream;

/**
 * @author Jeff Risberg (derived from Google ProtoBuf example - Java)
 * @since 01/04/16
 */
class ListPeople {
  // Iterates though all people in the AddressBook and prints info about them.
  static void print(AddressBook addressBook) {

    for (Person person: addressBook.getPersonList()) {
      System.out.println("Person ID: " + person.getId());
      System.out.println("  Name: " + person.getName());
      if (!person.getEmail().isEmpty()) {
        System.out.println("  E-mail address: " + person.getEmail());
      }

      for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
        switch (phoneNumber.getType()) {
          case MOBILE:
            System.out.print("  Mobile phone #: ");
            break;
          case HOME:
            System.out.print("  Home phone #: ");
            break;
          case WORK:
            System.out.print("  Work phone #: ");
            break;
        }
        System.out.println(phoneNumber.getNumber());
      }
    }
  }

  // Main function:  Reads the entire address book from a file and prints all
  //   the information inside.
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
      System.exit(-1);
    }

    // Read the existing address book.
    AddressBook addressBook =
      AddressBook.parseFrom(new FileInputStream(args[0]));

    print(addressBook);
  }
}