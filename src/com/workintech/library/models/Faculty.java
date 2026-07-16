package com.workintech.library.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Reader {

    public Faculty(long id, String name, String address, String phoneNo) {
        super(
                id,
                name,
                new ArrayList<Book>(),
                new MemberRecord(id, LocalDate.now(), 0, 10),
                address,
                phoneNo
        );
    }


}
