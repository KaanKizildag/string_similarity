/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.string_processing;

import cumle_benzerligi.entity.Kiyas;
import cumle_benzerligi.jaro.JaroWinkler;
import cumle_benzerligi.service.KiyasService;
import cumle_benzerligi.service.KiyasServiceImpl;
import cumle_benzerligi.ui.Frame;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author kaan
 */
public class StringProcessing {

    private KiyasService kiyasService = new KiyasServiceImpl();
    List<Kiyas> kiyasListesi = new ArrayList<>();
    Queue<Runnable> tasks = new LinkedList<>();

    public void slice(List<String> veriler) {

        // verinin kaç parçaya ayrılacağı veri boyutuna göre belirleniyor.
        int sliceNum = (int) (veriler.size() * .0001) + 1;

        for (int i = 0; i < sliceNum; i++) {
            List<String> sublist = veriler.subList(
                    (int) (veriler.size() * (i * 1. / sliceNum)),
                    (int) (veriler.size() * ((i + 1) * 1. / sliceNum))
            );

            for (int k = 0; k < sublist.size(); k++) {
                for (int j = k; j < veriler.size(); j++) {

                    String string1 = sublist.get(k);
                    String string2 = veriler.get(j);
                    if (string1.equals(string2)) {
                        continue;
                    }

                    float benzerlik = JaroWinkler.jaro_distance(string1, string2);

                    if (benzerlik < .75f) {
                        continue;
                    }

                    Kiyas kiyas = new Kiyas();
                    kiyas.setString1(string1);
                    kiyas.setString2(string2);
                    kiyas.setBenzerlik(benzerlik * 100);

                    // görev olarak ekleme yap
                    if (kiyasListesi.size() > 1000) {
                        gorevOlarakEkle(kiyasListesi);
                        kiyasListesi.clear();
                    }
                    kiyasListesi.add(kiyas);

                    Frame.addRowToTable(kiyas);
                }

                float ilerleme = 100 * (k * 1.f) / sublist.size();
                Frame.jProgressBar1.setValue((int) ilerleme);
                Frame.jProgressBar1.setString(
                        String.format("%d/%d %.2f", i, sliceNum, ilerleme));
            }
        }
        veriTabaninaEkle();
    }

    private void veriTabaninaEkle() {
        System.out.println("veri tabanına yazılıyor");
        new Thread(() -> {
            for (int i = 0; i < tasks.size(); i++) {
                tasks.poll().run();
                float ilerleme = (1.f * i) / tasks.size() * 100;
                Frame.jProgressBar1.setValue((int) ilerleme);
                Frame.jProgressBar1.setString(
                        String.format(
                                "veri tabanına yazılıyor: %.2f", ilerleme));
            }
            Frame.jProgressBar1.setValue(100);
            Frame.jProgressBar1.setString(
                    String.format("Tamamlandı"));
        }).start();
    }

    private void gorevOlarakEkle(Kiyas kiyas) {
        tasks.add(new Runnable() {
            @Override
            public void run() {
                kiyasService.insert(kiyas);
            }
        });
    }

    private void gorevOlarakEkle(List<Kiyas> kiyaslar) {
        tasks.add(new Runnable() {
            @Override
            public void run() {
                kiyasService.insert(kiyaslar);
            }
        });
    }
}
