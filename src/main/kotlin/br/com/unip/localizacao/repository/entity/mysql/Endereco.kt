package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Endereco {

    @Id
    var id: Long? = null

    @Column
    var latitude: Double

    @Column
    var longitude: Double

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }
}