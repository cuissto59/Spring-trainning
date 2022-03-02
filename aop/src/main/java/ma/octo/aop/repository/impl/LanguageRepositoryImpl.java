package ma.octo.aop.repository.impl;

import ma.octo.aop.entity.Language;
import ma.octo.aop.repository.LanguageRepository;
import ma.octo.aop.util.Logger;
import ma.octo.aop.util.impl.LoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class LanguageRepositoryImpl implements LanguageRepository {

  @Autowired
  private JdbcTemplate template ;

  public LanguageRepositoryImpl(JdbcTemplate template) {
    this.template = template;

  }


  private final List<Language> LANGUAGES = template.query("select * from LANGUAGES ",
          (rs, rowNum) -> new Language(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("fileExtension"))
  );




/*
  static {
    var java = new Language("java", "Java", "James Gosling", "java");
    var cpp = new Language("cpp", "C++", "Bjarne Stroustrup", "cpp");
    var cSharp = new Language("csharp", "C#", "Andres Hejlsberg", "cs");
    var perl = new Language("perl", "Perl", "Larry Wall", "pl");
    var haskell = new Language("haskel", "Haskell", "Simon Peyton", "hs");
    var lua = new Language("lua", "Lua", "Luiz Henrique", "lua");
    var python = new Language("python", "Python", "Guido van Rossum", "py");
    LANGUAGES = List.of(java, cpp, cSharp, perl, haskell, lua, python);
  }
  */

  @Override
  public Optional<Language> findByExtension(final String extension) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return LANGUAGES.stream()
        .filter(languageExtensionPredicate(extension))
        .findAny();
  }

  @Override
  public Optional<Language> findById(final String id) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return LANGUAGES.stream()
        .filter(languageIdPredicate(id))
        .findAny();
  }

  @Override
  public List<Language> findAll() {
    return LANGUAGES;
  }

  private Predicate<Language> languageExtensionPredicate(final String extension) {
    return language -> Objects.equals(extension, language.getFileExtension());
  }

  private Predicate<Language> languageIdPredicate(final String id) {
    return language -> Objects.equals(id, language.getId());
  }
}
