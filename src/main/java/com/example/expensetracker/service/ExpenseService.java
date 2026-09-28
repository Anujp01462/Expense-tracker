package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final Map<Long, Expense> expenses = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ExpenseService() {
        // Seed initial data
        addExpense(new Expense(null, "Freelance Web Project", 45000.0, "Salary", "INCOME", LocalDate.now().minusDays(10)));
        addExpense(new Expense(null, "Monthly Grocery Shopping", 6500.0, "Food", "EXPENSE", LocalDate.now().minusDays(5)));
        addExpense(new Expense(null, "Broadband Internet Bill", 1200.0, "Utilities", "EXPENSE", LocalDate.now().minusDays(3)));
        addExpense(new Expense(null, "Tech Conference Ticket", 3500.0, "Entertainment", "EXPENSE", LocalDate.now().minusDays(1)));
        addExpense(new Expense(null, "Metro Card Recharge", 1000.0, "Transport", "EXPENSE", LocalDate.now()));
    }

    public List<Expense> getAllExpenses(String category, String type) {
        return expenses.values().stream()
                .filter(e -> category == null || category.trim().isEmpty() || "ALL".equalsIgnoreCase(category) ||
                        e.getCategory().equalsIgnoreCase(category))
                .filter(e -> type == null || type.trim().isEmpty() || "ALL".equalsIgnoreCase(type) ||
                        e.getType().equalsIgnoreCase(type))
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Optional<Expense> getExpenseById(Long id) {
        return Optional.ofNullable(expenses.get(id));
    }

    public Expense addExpense(Expense expense) {
        Long id = idGenerator.incrementAndGet();
        expense.setId(id);
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        expenses.put(id, expense);
        return expense;
    }

    public Optional<Expense> updateExpense(Long id, Expense updated) {
        Expense existing = expenses.get(id);
        if (existing == null) return Optional.empty();

        if (updated.getTitle() != null) existing.setTitle(updated.getTitle().trim());
        if (updated.getAmount() != null) existing.setAmount(updated.getAmount());
        if (updated.getCategory() != null) existing.setCategory(updated.getCategory().trim());
        if (updated.getType() != null) existing.setType(updated.getType());
        if (updated.getDate() != null) existing.setDate(updated.getDate());

        expenses.put(id, existing);
        return Optional.of(existing);
    }

    public boolean deleteExpense(Long id) {
        return expenses.remove(id) != null;
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();
        List<Expense> all = new ArrayList<>(expenses.values());

        double totalIncome = all.stream()
                .filter(e -> "INCOME".equalsIgnoreCase(e.getType()))
                .mapToDouble(e -> e.getAmount() != null ? e.getAmount() : 0.0)
                .sum();

        double totalExpense = all.stream()
                .filter(e -> "EXPENSE".equalsIgnoreCase(e.getType()))
                .mapToDouble(e -> e.getAmount() != null ? e.getAmount() : 0.0)
                .sum();

        double balance = totalIncome - totalExpense;

        Map<String, Double> categoryTotals = all.stream()
                .filter(e -> "EXPENSE".equalsIgnoreCase(e.getType()))
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(e -> e.getAmount() != null ? e.getAmount() : 0.0)
                ));

        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("balance", balance);
        summary.put("categoryBreakdown", categoryTotals);

        return summary;
    }
}
