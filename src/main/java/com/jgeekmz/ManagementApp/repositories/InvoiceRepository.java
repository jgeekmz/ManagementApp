package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
