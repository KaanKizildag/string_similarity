/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.string_processing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaan
 */
public class ThreadController {

    List<Thread> threads = new ArrayList<>();

    void addThread(Thread t) {
        threads.add(t);
    }

    void run() {
        threads.forEach((t) -> {
            t.start();
        });
    }

    boolean isDead() {
        return threads.stream().noneMatch(t -> (t.isAlive()));
    }

}
