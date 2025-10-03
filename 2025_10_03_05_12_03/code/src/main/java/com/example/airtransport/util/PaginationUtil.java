package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Collections;

/**
 * Utility for paginating results.
 */
@Component
public class PaginationUtil {
    /**
     * Returns a sublist representing the requested page.
     * @param list List to paginate
     * @param page Page number (1-based)
     * @param size Page size
     * @param <T> Type
     * @return Paginated list
     */
    public <T> List<T> paginate(List<T> list, int page, int size) {
        if (list == null || list.isEmpty()) return Collections.emptyList();
        int fromIndex = (page - 1) * size;
        if (fromIndex >= list.size()) return Collections.emptyList();
        int toIndex = Math.min(fromIndex + size, list.size());
        return list.subList(fromIndex, toIndex);
    }
}
