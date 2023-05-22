import java.util.ArrayList;
import java.util.Scanner;

abstract class Rekening {
    protected String pemilik;
    protected int saldo;
    protected String nomor;

    public Rekening(String pemilik, int saldo, String nomor) {
        this.pemilik = pemilik;
        this.saldo = saldo;
        this.nomor = nomor;
    }

    public Rekening(String pemilik, int saldo) {
        this.pemilik = pemilik;
        this.saldo = saldo;
        this.nomor = generateNomor();
    }

    public String getPemilik() {
        return this.pemilik;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public String getNomor() {
        return this.nomor;
    }

    public void setor(int jumlah) {
        this.saldo += jumlah;
    }

    public abstract void tarik(int jumlah);

    public abstract void transfer(int jumlah, Rekening rekeningTujuan);

    public abstract int getLimitTarik();

    public abstract int getLimitTransfer();

    public static String generateNomor() {
        String nomor = "";
        for (int i = 0; i < 5; i++) {
            int digit = (int) (Math.random() * 10);
            nomor += digit;
        }
        return nomor;
    }
}

class RekeningEkspresi extends Rekening {
    private int limitTarik;
    private int limitTransfer;

    public RekeningEkspresi(String pemilik, int saldo, int limitTarik, int limitTransfer, String nomor) {
        super(pemilik, saldo, nomor);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public RekeningEkspresi(String pemilik, int saldo, int limitTarik, int limitTransfer) {
        super(pemilik, saldo);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public int getLimitTarik() {
        return this.limitTarik;
    }

    public int getLimitTransfer() {
        return this.limitTransfer;
    }

    public void tarik(int jumlah) {
        if (jumlah > this.limitTarik) {
            System.out.println("Limit tarik dana tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
        }
    }

    public void transfer(int jumlah, Rekening rekeningTujuan) {
        if (jumlah > this.limitTransfer) {
            System.out.println("Limit transfer tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
            rekeningTujuan.setor(jumlah);
        }
    }
}

class RekeningTahapan extends Rekening {
    private int limitTarik;
    private int limitTransfer;

    public RekeningTahapan(String pemilik, int saldo, int limitTarik, int limitTransfer, String nomor) {
        super(pemilik, saldo, nomor);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public RekeningTahapan(String pemilik, int saldo, int limitTarik, int limitTransfer) {
        super(pemilik, saldo);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public int getLimitTarik() {
        return this.limitTarik;
    }

    public int getLimitTransfer() {
        return this.limitTransfer;
    }

    public void tarik(int jumlah) {
        if (jumlah > this.limitTarik) {
            System.out.println("Limit tarik dana tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
        }
    }

    public void transfer(int jumlah, Rekening rekeningTujuan) {
        if (jumlah > this.limitTransfer) {
            System.out.println("Limit transfer tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
            rekeningTujuan.setor(jumlah);
        }
    }
}

class RekeningPrioritas extends Rekening {
    private int limitTarik;
    private int limitTransfer;

    public RekeningPrioritas(String pemilik, int saldo, int limitTarik, int limitTransfer, String nomor) {
        super(pemilik, saldo, nomor);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public RekeningPrioritas(String pemilik, int saldo, int limitTarik, int limitTransfer) {
        super(pemilik, saldo);
        this.limitTarik = limitTarik;
        this.limitTransfer = limitTransfer;
    }

    public int getLimitTarik() {
        return this.limitTarik;
    }

    public int getLimitTransfer() {
        return this.limitTransfer;
    }

    public void tarik(int jumlah) {
        if (jumlah > this.limitTarik) {
            System.out.println("Limit tarik dana tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
        }
    }

    public void transfer(int jumlah, Rekening rekeningTujuan) {
        if (jumlah > this.limitTransfer) {
            System.out.println("Limit transfer tidak mencukupi");
        } else if (this.saldo < jumlah) {
            System.out.println("Saldo tidak mencukupi");
        } else {
            this.saldo -= jumlah;
            rekeningTujuan.setor(jumlah);
        }
    }
}

public class banksimulasi {
    public static void main(String[] args) {
        ArrayList<Rekening> rekeningList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int pilihan = 0;

        while (pilihan != 7) {
            System.out.println("---------------------------------");
            System.out.println("|           MENU UTAMA          |");
            System.out.println("---------------------------------");
            System.out.println("|       1. Buat Rekening        |");
            System.out.println("|       2. Setor               |");
            System.out.println("|       3. Tarik               |");
            System.out.println("|       4. Transfer            |");
            System.out.println("|       5. Cari Rekening       |");
            System.out.println("|       6. Print               |");
            System.out.println("|       7. Keluar              |");
            System.out.println("---------------------------------");
            System.out.print("        Pilihan anda: ");
            pilihan = input.nextInt();

            if (pilihan == 1) {
                System.out.print("Masukkan nama pemilik: ");
                String pemilik = input.next();
                System.out.print("Masukkan saldo awal: ");
                int saldo = input.nextInt();
                System.out.println("Jenis Rekening:");
                System.out.println("1. Rekening Tahapan (limit transfer, tarik : 50000 , 10000)");
                System.out.println("2. Rekening Ekspresi (limit transfer,tarik: 25000 , 7000)");
                System.out.println("3. Rekening Prioritas (limit transfer,tarik: 100000 , 50000)");
                System.out.print("Pilih jenis rekening: ");
                int jenisRekening = input.nextInt();

                Rekening rekening = null;

                if (jenisRekening == 1) {
                    rekening = createRekeningTahapan(pemilik, saldo);
                } else if (jenisRekening == 2) {
                    rekening = createRekeningEkspresi(pemilik, saldo);
                } else if (jenisRekening == 3) {
                    rekening = createRekeningPrioritas(pemilik, saldo);
                } else {
                    System.out.println("Pilihan tidak valid");
                }

                if (rekening != null) {
                    rekeningList.add(rekening);
                    System.out.println("Rekening telah dibuat dengan nomor: " + rekening.getNomor());
                }
            } else if (pilihan == 2) {
                System.out.print("Masukkan nomor rekening: ");
                String nomor = input.next();
                Rekening rekening = findRekeningByNomor(rekeningList, nomor);

                if (rekening != null) {
                    System.out.print("Masukkan jumlah setoran: ");
                    int jumlah = input.nextInt();
                    rekening.setor(jumlah);
                    System.out.println("Setoran berhasil. Saldo saat ini: " + rekening.getSaldo());
                } else {
                    System.out.println("Rekening tidak ditemukan");
                }
            } else if (pilihan == 3) {
                System.out.print("Masukkan nomor rekening: ");
                String nomor = input.next();
                Rekening rekening = findRekeningByNomor(rekeningList, nomor);

                if (rekening != null) {
                    System.out.print("Masukkan jumlah penarikan: ");
                    int jumlah = input.nextInt();
                    rekening.tarik(jumlah);
                    System.out.println("Penarikan berhasil. Saldo saat ini: " + rekening.getSaldo());
                } else {
                    System.out.println("Rekening tidak ditemukan");
                }
            } else if (pilihan == 4) {
                System.out.print("Masukkan nomor rekening pengirim: ");
                String nomorPengirim = input.next();
                System.out.print("Masukkan nomor rekening penerima: ");
                String nomorPenerima = input.next();
                Rekening rekeningPengirim = findRekeningByNomor(rekeningList, nomorPengirim);
                Rekening rekeningPenerima = findRekeningByNomor(rekeningList, nomorPenerima);

                if (rekeningPengirim != null && rekeningPenerima != null) {
                    System.out.print("Masukkan jumlah transfer: ");
                    int jumlah = input.nextInt();
                    rekeningPengirim.transfer(jumlah, rekeningPenerima);
                    System.out.println("Transfer berhasil. Saldo " + rekeningPengirim.getNomor() + ": " + rekeningPengirim.getSaldo());
                    System.out.println("Saldo " + rekeningPenerima.getNomor() + ": " + rekeningPenerima.getSaldo());
                } else {
                    System.out.println("Rekening tidak ditemukan");
                }
            } else if (pilihan == 5) {
                System.out.print("Masukkan nomor rekening: ");
                String nomor = input.next();
                Rekening rekening = findRekeningByNomor(rekeningList, nomor);

                if (rekening != null) {
                    System.out.println("Nomor Rekening: " + rekening.getNomor());
                    System.out.println("Nama Pemilik: " + rekening.getPemilik());
                    System.out.println("Saldo: " + rekening.getSaldo());
                    System.out.println("Limit Tarik: " + rekening.getLimitTarik());
                    System.out.println("Limit Transfer: " + rekening.getLimitTransfer());
                } else {
                    System.out.println("Rekening tidak ditemukan");
                }
            } else if (pilihan == 6) {
                System.out.println("Daftar Rekening:");
                for (Rekening rekening : rekeningList) {
                    System.out.println("Nomor Rekening: " + rekening.getNomor());
                    System.out.println("Nama Pemilik: " + rekening.getPemilik());
                    System.out.println("Saldo: " + rekening.getSaldo());
                    System.out.println("Limit Tarik: " + rekening.getLimitTarik());
                    System.out.println("Limit Transfer: " + rekening.getLimitTransfer());
                    System.out.println("---------------------------------");
                }
            } else if (pilihan == 7) {
                System.out.println("Terima kasih!");
            } else {
                System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static Rekening createRekeningTahapan(String pemilik, int saldo) {
        return new RekeningTahapan(pemilik, saldo, 10000, 50000);
    }

    public static Rekening createRekeningEkspresi(String pemilik, int saldo) {
        return new RekeningEkspresi(pemilik, saldo, 7000, 25000);
    }

    public static Rekening createRekeningPrioritas(String pemilik, int saldo) {
        return new RekeningPrioritas(pemilik, saldo, 50000, 100000);
    }

    public static Rekening findRekeningByNomor(ArrayList<Rekening> rekeningList, String nomor) {
        for (Rekening rekening : rekeningList) {
            if (rekening.getNomor().equals(nomor)) {
                return rekening;
            }
        }
        return null;
    }
}
