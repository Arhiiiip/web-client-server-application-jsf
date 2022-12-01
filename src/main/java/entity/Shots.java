package entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "shots")
@Table(name="shots")
@Data
@Getter
@Setter
public class Shots {

    public Shots(){

    }
    @Id
    @Column(name="shots_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "next_shots_id")
    private int id;

    @Column(name="x")
    private Float x;

    @Column(name="y")
    private Float y;

    @Column(name="r")
    private Float r;

    @Column(name="currentTime")
    private String currentTime;

    @Column(name="duration")
    private long duration;

    @Column(name="result")
    private boolean result;
}
