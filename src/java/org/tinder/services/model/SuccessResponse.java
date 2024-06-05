/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author emilio
 */
@Data
@AllArgsConstructor
public class SuccessResponse {
    private String status;
    private String message;
}
