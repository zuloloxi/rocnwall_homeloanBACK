package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.Borrower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowerAdapter {

    public static List<Borrower> transformToBorrowerList(List<BorrowerDTO> borrowerDTO) {
        if (borrowerDTO == null) {
            return new ArrayList<>();
        }
        return borrowerDTO.stream().map(BorrowerAdapter::transformToBorrower).collect(Collectors.toList());
    }

    public static Borrower transformToBorrower(BorrowerDTO borrowerDTO) {
        return new Borrower(null, borrowerDTO.netIncome, borrowerDTO.dateOfBirth);
    }

    public static List<BorrowerDTO> adaptToBorrowerListDTO(List<Borrower> borrowers) {
        return borrowers.stream().map(BorrowerAdapter::adaptToBorrowerDTO).collect(Collectors.toList());
    }

    public static BorrowerDTO adaptToBorrowerDTO(Borrower borrower) {
        return new BorrowerDTO(
                borrower.getNetIncome(),
                borrower.getDateOfBirth()
        );
    }
}
