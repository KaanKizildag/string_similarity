/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.service;

import cumle_benzerligi.entity.Kiyas;
import java.util.List;

/**
 *
 * @author kaan
 */
public interface KiyasService {

    List<Kiyas> getKiyasOrderByBenzerlikDesc();

    void insert(Kiyas kiyas);

    void insert(List<Kiyas> kiyaslar);
}
