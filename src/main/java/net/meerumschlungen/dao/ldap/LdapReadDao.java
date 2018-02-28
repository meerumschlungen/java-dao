package net.meerumschlungen.dao.ldap;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import net.meerumschlungen.dao.ReadDao;

public interface LdapReadDao<T> extends LdapDao<T>, ReadDao<T> {
    @SuppressWarnings("nls")
    public default String getSearchName() {
        return "";
    }
    public String getSearchFilter();
    public SearchControls getSearchControls();
    public Function<SearchResult, T> getSearchResultMapper();

    @Override
    public default List<T> read() {
        try {
            return Collections
                    .list(this.getLdapContext().search(this.getSearchName(), this.getSearchFilter(),
                            this.getSearchControls()))
                    .stream().map(this.getSearchResultMapper()).collect(Collectors.toList());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
