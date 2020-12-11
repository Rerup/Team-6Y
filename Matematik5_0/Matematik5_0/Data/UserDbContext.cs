using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Matematik5_0.Models.WebModel;

namespace Matematik5_0.Data
{
    public class UserDbContext : IdentityDbContext
    {
        public UserDbContext(DbContextOptions<UserDbContext> options)
            : base(options)
        {
            Database.Migrate();
        }
        public DbSet<ExcerciseCategory> ExcerciseCategory { get; set; }
    }
}
