package net.meerumschlungen.dao.ldap;

import javax.naming.ldap.LdapContext;

public interface LdapDao<T> {
    public LdapContext getLdapContext();
}
