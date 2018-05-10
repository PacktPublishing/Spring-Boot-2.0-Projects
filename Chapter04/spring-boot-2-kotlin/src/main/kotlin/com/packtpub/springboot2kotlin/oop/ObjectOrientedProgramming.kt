/*
    Copyright (C) 2018  Shazin Sadakath

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.packtpub.springboot2kotlin.oop

class ObjectOrientedProgramming {

    object BeanRegister {
        fun registerBean(bean: Any) {
            println("$bean is registered");
        }
    }

    object PettableSingleton : Pettable {
        override fun play() {
            super.play()
        }
    }

    fun run() {
        var animal1 : Animal = Cat("Meow");
        var animal2 : Animal = Lion("Roar");

        var animals = listOf(animal1, animal2);

        for (animal in animals) {
            if (animal is Pettable) {
                println(animal.makeNoise());
            } else {
                println("${animal.name()} is not a Pet");
            }
        }

        println("aaaaabcdefghijkl".countAs());

        var intRange : Range<Int, Int> = Range<Int, Int>(1, 10);
        var doubleRange : Range<Double, Double> = Range<Double, Double>(1.0, 10.0);

        println(intRange);
        println(doubleRange);

        var tamedLionPet = object : Lion("roar"), Pettable {
            override fun name() : String {
                return "Tamed Pet Lion";
            }
        }

        var personObjectWithoutAClass = object {
            var name : String = "Shazin"
            var age : Int = 32
        }

        BeanRegister.registerBean("String bean");

        PettableSingleton.play();

        var bean = Bean.create();
    }

    fun String.countAs() : Int {
        var count = 0;
        for(i in 0..(this.length - 1)) {
            if (this.get(i) == 'a') {
                count++;
            }
        }
        return count;
    }

}
