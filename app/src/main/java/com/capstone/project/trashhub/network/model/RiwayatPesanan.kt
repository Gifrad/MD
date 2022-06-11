package com.capstone.project.trashhub.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatPesanan (
    @SerializedName("alamat_bank_sampah")
    var alamatBankSampah : String,

    @SerializedName("alamat_user")
    var alamatUser : String,

    @SerializedName("banksampah_pict")
    var fotoBankSampah : String,

    @SerializedName("bayar_transaksi")
    var biaya : String,

    @SerializedName("catatan")
    var catatan : String,

    @SerializedName("durasi_transaksi")
    var durasiTransaksi : String,


    @SerializedName("foto_sampah")
    var fotoSampah : String,


    @SerializedName("id_bank_sampah")
    var idBankSampah : String,

    @SerializedName("id_user")
    var idUser : String,

    @SerializedName("nama_bank_sampah")
    var name : String,

    @SerializedName("name")
    var nameUser : String,

    @SerializedName("no_hp")
    var noHp : String,

    @SerializedName("tanggal_pesan")
    var tanggalPesan : String,

):Parcelable

data class RiwayatPesanan2 (
    @SerializedName("alamat_bank_sampah")
    var alamatBankSampah : String ?= null,

    @SerializedName("alamat_user")
    var alamatUser : String ?= null,

    @SerializedName("banksampah_pict")
    var fotoBankSampah : String ?= null,

    @SerializedName("bayar_transaksi")
    var biaya : String ?= null,

    @SerializedName("catatan")
    var catatan : String ?= null,

    @SerializedName("durasi_transaksi")
    var durasiTransaksi : String ?= null,


    @SerializedName("foto_sampah")
    var fotoSampah : String ?= null,


    @SerializedName("id_bank_sampah")
    var idBankSampah : String ?= null,

    @SerializedName("id_user")
    var idUser : String ?= null,

    @SerializedName("nama_bank_sampah")
    var name : String ?= null,

    @SerializedName("name")
    var nameUser : String ?= null,

    @SerializedName("no_hp")
    var noHp : String ?= null,

    @SerializedName("tanggal_pesan")
    var tanggalPesan : String ?= null,

)

