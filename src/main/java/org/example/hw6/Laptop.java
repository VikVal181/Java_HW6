package org.example.hw6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {
    String name = "";
    String color = "";
    String cpu_name = "";
    Double cpu_frequency = 0.0;
    Integer ram_size = 0;
    Integer hdd_size = 0;
    Double price = 0.0;

    public boolean isEquals(Laptop other) {
        if (this.name.length() != 0 && !this.name.toLowerCase().equals(other.name.toLowerCase())) {
            return false;
        } else if (this.color.length() != 0 && !this.color.toLowerCase().equals(other.color.toLowerCase())) {
            return false;
        } else if (this.cpu_name.length() != 0 && !this.cpu_name.toLowerCase().equals(other.cpu_name.toLowerCase())) {
            return false;
        } else if (this.cpu_frequency != 0.0 && this.cpu_frequency > other.cpu_frequency) {
            return false;
        } else if (this.ram_size != 0 && this.ram_size > other.ram_size) {
            return false;
        } else if (this.hdd_size != 0 && this.hdd_size > other.hdd_size) {
            return false;
        }
        else return true;

    }

    public void print()  {
        System.out.printf("%-8s %-5s %-5s %5s %5s %5s %8s\n", this.name, this.color, this.cpu_name,
                this.cpu_frequency, this.ram_size, this.hdd_size, this.price);
    }
}
