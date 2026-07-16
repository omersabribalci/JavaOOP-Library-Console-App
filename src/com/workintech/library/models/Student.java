package com.workintech.library.models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Student extends Reader {


    public Student(long id, String name, String address, String phoneNo) {

        super(
                id,
                name,
                new ArrayList<Book>(),
                new MemberRecord(id, LocalDate.now(), 0, 5),
                address,
                phoneNo
        );
    }

}
