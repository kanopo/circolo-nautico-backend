package me.ollari.backendesamejava;

import me.ollari.backendesamejava.AnnualFee.AnnualFee;
import me.ollari.backendesamejava.AnnualFee.AnnualFeeRepository;
import me.ollari.backendesamejava.Boat.Boat;
import me.ollari.backendesamejava.Boat.BoatRepository;
import me.ollari.backendesamejava.Employee.Employee;
import me.ollari.backendesamejava.Employee.EmployeeRepository;
import me.ollari.backendesamejava.Member.Member;
import me.ollari.backendesamejava.Member.MemberRepository;
import me.ollari.backendesamejava.ParkingFee.ParkingFee;
import me.ollari.backendesamejava.ParkingFee.ParkingFeeRepository;
import me.ollari.backendesamejava.Race.Race;
import me.ollari.backendesamejava.Race.RaceRepository;
import me.ollari.backendesamejava.RaceFee.RaceFee;
import me.ollari.backendesamejava.RaceFee.RaceFeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApi {

    private final EmployeeRepository employeeRepository;
    private final MemberRepository memberRepository;
    private final BoatRepository boatRepository;
    private final RaceRepository raceRepository;
    private final RaceFeeRepository raceFeeRepository;
    private final ParkingFeeRepository parkingFeeRepository;
    private final AnnualFeeRepository annualFeeRepository;


    public RestApi(EmployeeRepository employeeRepository, MemberRepository memberRepository, BoatRepository boatRepository, RaceRepository raceRepository, RaceFeeRepository raceFeeRepository, ParkingFeeRepository parkingFeeRepository, AnnualFeeRepository annualFeeRepository) {
        this.employeeRepository = employeeRepository;
        this.memberRepository = memberRepository;
        this.boatRepository = boatRepository;
        this.raceRepository = raceRepository;
        this.raceFeeRepository = raceFeeRepository;
        this.parkingFeeRepository = parkingFeeRepository;
        this.annualFeeRepository = annualFeeRepository;
    }
    //TODO: MEMBER
    @GetMapping("/get/member")
    List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    @GetMapping("/get/member/username/{username}")
    Member findMemberByUsername(@PathVariable String username){
        List<Member> all = memberRepository.findAll();
        for (Member member:all){
            if (member.getUsername().equals(username)){
                return member;
            }
        }
        return null;
    }

    @GetMapping("/get/member/member-id/{memberId}")
    Member findMemberById(@PathVariable Integer memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }



    @PostMapping("/post/member")
    Member createMember(@RequestBody Member member){
        return memberRepository.save(member);
    }

    @DeleteMapping("/delete/member/member-id/{memberId}")
    void delMember(@PathVariable Integer memberId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        memberRepository.delete(member);
    }


    //TODO: EMPLOYEE
    @GetMapping("/get/employee")
    List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/post/employee")
    Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/delete/employee/employee-id/{employeeId}")
    void delEmployee(@PathVariable Integer employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employeeRepository.delete(employee);
    }

    //TODO: BOAT
    @GetMapping("/get/boat")
    List<Boat> getAllBoats(){
        return boatRepository.findAll();
    }

    @PutMapping("/put/boat/add-boat/member-id/{memberId}")
    void addBoatToMember(@PathVariable Integer memberId, @RequestBody Boat boat){
        Member member = memberRepository.findById(memberId).orElseThrow();
        boat.setMember(member);
        boatRepository.save(boat);
    }

    @DeleteMapping("/delete/boat/boat-id/{boatId}")
    void delBoat(@PathVariable Integer boatId){
        Boat boat = boatRepository.findById(boatId).orElseThrow();
        boatRepository.delete(boat);
    }

    //TODO: RACE
    @GetMapping("/get/race")
    List<Race> getAllRaces(){
        return raceRepository.findAll();
    }

    @PostMapping("/post/race")
    Race createRace(@RequestBody Race race){
        return raceRepository.save(race);
    }


    //TODO: RACE-FEE
    @GetMapping("/get/race-fee")
    List<RaceFee> getAllRaceFee(){
        return raceFeeRepository.findAll();
    }

    @PutMapping("/put/race-fee/add-race-fee/race-id/{raceId}/member-id/{memberId}/boat-id/{boatId}")
    void addRaceFee(@PathVariable Integer memberId, @PathVariable Integer boatId, @PathVariable Integer raceId, @RequestBody RaceFee raceFee){
        Member member = memberRepository.findById(memberId).orElseThrow();
        Boat boat = boatRepository.findById(boatId).orElseThrow();
        Race race = raceRepository.findById(raceId).orElseThrow();
        raceFee.setMember(member);
        raceFee.setBoat(boat);
        raceFee.setRace(race);
        raceFeeRepository.save(raceFee);
    }

    //TODO: PARKING-FEE
    @GetMapping("/get/parking-fee")
    List<ParkingFee> getAllParkingFee(){
        return parkingFeeRepository.findAll();
    }

    @PutMapping("/put/parking-fee/add-parking-fee/member-id/{memberId}/boat-id/{boatId}")
    void addParkingFeeToBoatMember(@PathVariable Integer memberId, @PathVariable Integer boatId, @RequestBody ParkingFee parkingFee){
        Member member = memberRepository.findById(memberId).orElseThrow();
        Boat boat = boatRepository.findById(boatId).orElseThrow();
        parkingFee.setMember(member);
        parkingFee.setBoat(boat);
        parkingFeeRepository.save(parkingFee);
    }

    //TODO: ANNUAL-FEE
    @GetMapping("/get/annual-fee")
    List<AnnualFee> getAllAnnualFee(){
        return annualFeeRepository.findAll();
    }

    @PutMapping("/put/annual-fee/add-annual-fee/member-id/{memberId}")
    void addAnnualFeeToMember(@PathVariable Integer memberId, @RequestBody AnnualFee annualFee){
        Member member = memberRepository.findById(memberId).orElseThrow();
        annualFee.setMember(member);
        annualFeeRepository.save(annualFee);
    }
}
