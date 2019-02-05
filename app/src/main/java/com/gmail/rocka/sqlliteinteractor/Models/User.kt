package com.gmail.rocka.sqlliteinteractor.Models

class User {
    var name: String = ""
    var id: Int = 0
    var age: Int = 0

    constructor(Name: String, Age: Int) {
        this.name = Name
        this.age = Age
    }

    constructor(){
    }
}