package com.workintech.library.ui;

import com.workintech.library.models.*;
import com.workintech.library.services.Library;

import java.time.LocalDate;

public class DataSeeder {
    public static void seed(Library library) {
        // Yazarlar
        Author tolkien = new Author(1, "J.R.R. Tolkien");
        Author orwell = new Author(2, "George Orwell");
        Author asimov = new Author(3, "Isaac Asimov");

        // Kitaplar — farklı alt tiplerden karışık örnekler
        library.addNewBook(new Book(101, tolkien, "The Hobbit", 120.0, "1st", LocalDate.of(2020, 3, 10)));
        library.addNewBook(new Book(102, orwell, "1984", 95.0, "3rd", LocalDate.of(2019, 6, 1)));
        library.addNewBook(new StudyBooks(103, asimov, "Foundation", 150.0, "2nd", LocalDate.of(2021, 1, 15)));
        library.addNewBook(new Journals(104, asimov, "Science Weekly", 40.0, "Vol.12", LocalDate.of(2023, 9, 5)));
        library.addNewBook(new Magazines(105, orwell, "Politics Today", 25.0, "Issue 45", LocalDate.of(2023, 11, 2)));

        // Okuyucular
        library.addNewReader(new Student(201, "Ahmet Yılmaz", "İzmir", "05551112233"));
        library.addNewReader(new Faculty(202, "Prof. Ayşe Kaya", "Ankara", "05339998877"));

        System.out.println("\n***************************************************\n");
    }
}
