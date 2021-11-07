/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author kaan
 */
@Entity
@Data
@ToString
public class Kiyas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String string1;
    private String string2;
    private float benzerlik;

}
