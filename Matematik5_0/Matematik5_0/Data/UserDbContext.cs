using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Matematik5_0.Models.Forum;

namespace Matematik5_0.Data
{
    public class UserDbContext : IdentityDbContext
    {
        public UserDbContext(DbContextOptions<UserDbContext> options)
            : base(options)
        {
            Database.Migrate();
        }
        public DbSet<Matematik5_0.Models.Forum.Category> Category { get; set; }
        public DbSet<Matematik5_0.Models.Forum.Post> Post { get; set; }
        public DbSet<Matematik5_0.Models.Forum.Comment> Comment { get; set; }
       
    }
}
